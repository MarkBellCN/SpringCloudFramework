package com.hollysys.platform.common.web.proxy;

import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.utils.ReferenceConfigCache;
import org.apache.dubbo.rpc.service.GenericService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RpcProxyServiceFactory {
    private static class SingletonHolder {
        private static RpcProxyServiceFactory INSTANCE = new RpcProxyServiceFactory();
    }

    public static RpcProxyServiceFactory getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public Object invoke(String interfaceClass, String methodName, Map<String, Object>  params){
        List<Map<String, Object>> list = new ArrayList<>();
        list.add(params);
        return invoke(interfaceClass,methodName,list);
    }

    public Object invoke(String interfaceClass, String methodName, List<Map<String, Object>>  params){
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
        int len = params.size();
        String[] invokeParamTypes = new String[len];
        Object[] invokeParams = new Object[len];
        for(int i = 0; i < len; i++){
            invokeParamTypes[i] = params.get(i).get("paramType")+ "";
            invokeParams[i] = params.get(i).get("paramValue");
        }
        return genericService.$invoke(methodName, invokeParamTypes, invokeParams);
    }
}
