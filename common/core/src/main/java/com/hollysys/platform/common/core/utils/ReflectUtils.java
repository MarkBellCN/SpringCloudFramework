package com.hollysys.platform.common.core.utils;

import java.lang.reflect.Method;

public class ReflectUtils {

    public static String[] getParamTypesByMethod(String interfaceClass, String methodName, int paramsCount) throws ClassNotFoundException {
        String[] paramTypes = new String[paramsCount];
        Class clazz = Class.forName(interfaceClass);
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            if (methodName.equals(method.getName()) && paramsCount == method.getParameterCount()) {
                Class<?>[] types = method.getParameterTypes();
                for (int i = 0; i < paramsCount; i++) {
                    paramTypes[i] = types[i].getName();
                }
            }
        }
        return paramTypes;
    }
}
