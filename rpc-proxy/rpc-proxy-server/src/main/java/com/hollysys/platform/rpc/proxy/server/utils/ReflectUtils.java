package com.hollysys.platform.rpc.proxy.server.utils;

import com.hollysys.platform.rpc.proxy.server.cache.RpcProxyCache;
import com.hollysys.platform.rpc.proxy.server.entity.RpcProxyEntity;

import java.lang.reflect.Method;

public class ReflectUtils {

    public static RpcProxyEntity getParamTypesByMethod(String interfaceClass, String methodName, int count) throws ClassNotFoundException {
        RpcProxyEntity rpcProxyEntity = new RpcProxyEntity();
        String[] source = new String[count];
        String[] target = new String[count];
        Class clazz = Class.forName(interfaceClass);
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            if (methodName.equals(method.getName()) && count == method.getParameterCount()) {
                Class<?>[] types = method.getParameterTypes();
                for (int i = 0; i < count; i++) {
                    source[i] = types[i].getName();
                    target[i] = RpcProxyCache.getParamType(types[i].getName());
                }
            }
        }
        rpcProxyEntity.setSource(source);
        rpcProxyEntity.setTarget(target);
        return rpcProxyEntity;
    }
}
