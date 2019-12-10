package com.hollysys.platform.rpc.proxy.api.provider;

public interface RpcProxyProvider {
    public String invoke(String className, String methodName, Object... params) throws ClassNotFoundException;
}