package com.hollysys.platform.rpc.proxy.server.cache;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class ParamTypeCache {
    private static Map<String,String> PARAM_TYPE = new HashMap<>();

    static {
        PARAM_TYPE.put(IPage.class.getName(), Page.class.getName());
    }

    public static String getParamType(String sourceType){
        String targetType = PARAM_TYPE.get(sourceType);
        if(!StringUtils.isNotBlank(targetType)){
            targetType = sourceType;
        }
        return targetType;
    }
}
