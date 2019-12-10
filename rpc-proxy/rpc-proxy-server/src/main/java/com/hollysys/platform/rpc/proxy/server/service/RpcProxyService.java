package com.hollysys.platform.rpc.proxy.server.service;

import com.alibaba.fastjson.JSONObject;
import com.hollysys.platform.rpc.proxy.api.provider.RpcProxyProvider;
import com.hollysys.platform.rpc.proxy.server.utils.ReflectUtils;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.annotation.Service;
import org.apache.dubbo.config.utils.ReferenceConfigCache;
import org.apache.dubbo.rpc.service.GenericService;
import org.apache.thrift.TException;

@Service(protocol = "dubbo")
public class RpcProxyService implements RpcProxyProvider.Iface {

    private String invoke(String interfaceClass, String methodName, Object... params) throws ClassNotFoundException {
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
        String[] invokeParamTypes = ReflectUtils.getParamTypesByMethod(interfaceClass,methodName,params);
        return JSONObject.toJSONString(genericService.$invoke(methodName, invokeParamTypes, params));
    }

    @Override
    public String invoke(String interfaceClass, String methodName, String params) throws TException {
        return invoke(interfaceClass,methodName,params);
    }
}
