package com.hollysys.platform.rpc.proxy.server.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import com.hollysys.platform.common.core.vo.Result;
import com.hollysys.platform.rpc.proxy.api.provider.RpcProxyProvider;
import com.hollysys.platform.rpc.proxy.server.cache.RpcProxyCache;
import com.hollysys.platform.rpc.proxy.server.entity.RpcProxyEntity;
import com.hollysys.platform.rpc.proxy.server.utils.ReflectUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.annotation.Service;
import org.apache.dubbo.config.utils.ReferenceConfigCache;
import org.apache.dubbo.rpc.service.GenericService;
import org.apache.thrift.TException;

import java.util.List;


@Slf4j
@Service(protocol = "dubbo")
public class RpcProxyService implements RpcProxyProvider.Iface {
    @Override
    public String invoke(String serverName, String providerName, String methodName, List<String> params) throws TException {
        String interfaceClass = RpcProxyCache.getServerType(serverName)+"."+providerName;
        try {
            return invokeDubbo(interfaceClass,methodName,params);
        } catch (ClassNotFoundException e) {
            log.error("RpcProxyService invoke ClassNotFoundException",e);
        }
        return JSON.toJSONString(Result.fail());
    }

    private String invokeDubbo(String interfaceClass, String methodName, List<String> params) throws ClassNotFoundException {
        ReferenceConfig<GenericService> reference = new ReferenceConfig<GenericService>();
        reference.setInterface(interfaceClass); // 接口名
        reference.setGeneric(true); // 声明为泛化接口
        //ReferenceConfig实例很重，封装了与注册中心的连接以及与提供者的连接，
        //需要缓存，否则重复生成ReferenceConfig可能造成性能问题并且会有内存和连接泄漏。
        //API方式编程时，容易忽略此问题。
        //这里使用dubbo内置的简单缓存工具类进行缓存
        ReferenceConfigCache cache = ReferenceConfigCache.getCache();
        GenericService genericService = cache.get(reference);
        // 用com.alibaba.dubbo.rpc.service.GenericService可以替代所有接口引用
        RpcProxyEntity rpcProxyEntity = ReflectUtils.getParamTypesByMethod(interfaceClass,methodName,params.size());
        String[] targetTypes =rpcProxyEntity.getTarget();
        Object[] invokeValue = new Object[params.size()];
        for(int i=0;i<targetTypes.length;i++){
            Class clazz = Class.forName(targetTypes[i]);
            invokeValue[i] = JSONObject.parseObject(params.get(i),clazz);
        }
        return JSONObject.toJSONString(genericService.$invoke(methodName, rpcProxyEntity.getSource(), invokeValue));
    }
}
