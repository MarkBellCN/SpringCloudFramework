namespace java com.hollysys.platform.rpc.proxy.api.provider
service RpcProxyProvider{
    string invoke(
		1:string serverName, 
		2:string providerName, 
		3:string methodName, 
		4:list<string> params
	)
}