package com.hollysys.iods.common.web.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class NetworkUtils {
    private NetworkUtils(){}
    private static final String[] headers = {"x-forwarded-for", "Request-Client-IP", "Proxy-Client-IP", "WL-Proxy-Client-IP", "HTTP_CLIENT_IP", "X-Real-IP", "HTTP_X_FORWARDED_FOR"};

    public static String getRemoteIpAddress(HttpServletRequest request) {

        String ip = null;

        for (String header : headers) {
            ip = request.getHeader(header);
            if (StringUtils.isNotBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
                break;
            }
        }

        // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if (ip != null && ip.length() > 15) {
            if (ip.indexOf(",") > 0) {
                ip = ip.substring(0, ip.indexOf(","));
            }
        }
        return ip != null ? ip : request.getRemoteAddr();
    }

    public static String macAddress() {

        try {
            Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces();
            StringBuilder sb = new StringBuilder();
            List<String> tmpMacList = new ArrayList<>();
            while(en.hasMoreElements()){
                NetworkInterface iface = en.nextElement();
                List<InterfaceAddress> addrs = iface.getInterfaceAddresses();
                for(InterfaceAddress addr : addrs) {
                    InetAddress ip = addr.getAddress();
                    NetworkInterface network = NetworkInterface.getByInetAddress(ip);
                    if(network == null) {
                        continue;
                    }
                    byte[] mac = network.getHardwareAddress();
                    if(mac == null) {
                        continue;
                    }
                    sb.delete(0, sb.length());
                    for (int i = 0; i < mac.length; i++) {
                        sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
                    }
                    tmpMacList.add(sb.toString());
                }
            }
            if(CollectionUtils.isEmpty(tmpMacList)){
                return "unknown";
            }
            List<String> unique = tmpMacList.stream().distinct().collect(Collectors.toList());
            return unique.get(0);
        } catch (Exception e) {
            return "unknown";
        }
    }
}
