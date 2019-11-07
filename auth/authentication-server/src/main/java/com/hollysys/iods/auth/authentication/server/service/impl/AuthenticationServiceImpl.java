package com.hollysys.iods.auth.authentication.server.service.impl;

import com.hollysys.iods.auth.authentication.server.service.AuthenticationService;
import com.hollysys.iods.auth.authentication.server.service.ResourceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {
    public static final String NONEXISTENT_URL = "NONEXISTENT_URL";

    @Autowired
    private ResourceService resourceService;

    /**
     *
     * @param authRequest authRequest 访问的url,method
     * @return 有权限true, 无权限或全局资源中未找到请求url返回否
     */
    @Override
    public boolean decide(HttpServletRequest authRequest) {
        log.debug("正在访问的url是:{}，method:{}", authRequest.getServletPath(), authRequest.getMethod());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        ConfigAttribute urlConfigAttribute = resourceService.findConfigAttributesByUrl(authRequest);
        return true;
    }
}
