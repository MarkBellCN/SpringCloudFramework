namespace java com.hollysys.platform.rpc.proxy.api.provider
service RpcProxyProvider{
    string invoke(1:string interfaceClass, 2:string methodName, 3:string params)
}