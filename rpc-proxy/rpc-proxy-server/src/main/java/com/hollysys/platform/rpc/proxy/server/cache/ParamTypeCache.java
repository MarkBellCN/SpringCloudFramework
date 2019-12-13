package com.hollysys.platform.rpc.proxy.server.cache;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class ParamTypeCache {
    private static Map<String,String> PARAM_TYPE = new HashMap<>();
    private static Map<String,String> SERVER_TYPE = new HashMap<>();

    static {
        PARAM_TYPE.put(IPage.class.getName(), Page.class.getName());
    }

    static {
        SERVER_TYPE.put("auth-data-server", "com.hollysys.iods.data.api.provider");
    }

    public static String getParamType(String sourceType){
        String targetType = PARAM_TYPE.get(sourceType);
        if(!StringUtils.isNotBlank(targetType)){
            targetType = sourceType;
        }
        return targetType;
    }
}
