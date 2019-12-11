package com.hollysys.platform.rpc.proxy.server.utils;

import com.hollysys.platform.rpc.proxy.server.cache.ParamTypeCache;

import java.lang.reflect.Method;

public class ReflectUtils {

    public static String[] getParamTypesByMethod(String interfaceClass, String methodName, Object... params) throws ClassNotFoundException {
        String[] paramTypes = new String[params.length];
        Class clazz = Class.forName(interfaceClass);
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            if (methodName.equals(method.getName()) && params.length == method.getParameterCount()) {
                Class<?>[] types = method.getParameterTypes();
                for (int i = 0; i < params.length; i++) {
                    paramTypes[i] = ParamTypeCache.getParamType(types[i].getName());
                }
            }
        }
        return paramTypes;
    }
}
