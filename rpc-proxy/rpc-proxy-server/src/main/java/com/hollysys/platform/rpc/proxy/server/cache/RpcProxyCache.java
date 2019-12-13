package com.hollysys.platform.rpc.proxy.server.cache;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class RpcProxyCache {
    public static Map<String,String> PARAM_TYPE = new HashMap<>();
    public static Map<String,String> SERVER_TYPE = new HashMap<>();

    public static String getParamType(String sourceType){
        String targetType = PARAM_TYPE.get(sourceType);
        if(!StringUtils.isNotBlank(targetType)){
            targetType = sourceType;
        }
        return targetType;
    }

    public static String getServerType(String sourceType){
        String targetType = SERVER_TYPE.get(sourceType);
        if(!StringUtils.isNotBlank(targetType)){
            targetType = sourceType;
        }
        return targetType;
    }
}
