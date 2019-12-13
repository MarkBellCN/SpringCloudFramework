package com.hollysys.platform.rpc.proxy.server.config;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;
import com.hollysys.platform.rpc.proxy.server.cache.RpcProxyCache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;

@Slf4j
@Component
public class DynamicProxyConfig implements ApplicationEventPublisherAware {
    private static final String DATA_ID = "rpc-proxy-config.json";
    private static final String GROUP = "DEFAULT_GROUP";


    private ApplicationEventPublisher applicationEventPublisher;

    @Value("${spring.cloud.nacos.config.server-addr}")
    private String serverAddr;

    @Bean
    public void refreshRouting() throws NacosException {
        ConfigService configService = NacosFactory.createConfigService(serverAddr);
        String configInfo = configService.getConfig(DATA_ID, GROUP, 5000);
        loadConfigByString(configInfo);
        configService.addListener(DATA_ID, GROUP, new Listener() {
            @Override
            public Executor getExecutor() {
                return null;
            }
            @Override
            public void receiveConfigInfo(String configInfo) {
                loadConfigByString(configInfo);
            }
        });
    }

    private void loadConfigByString(String configInfo){
        log.info("加载代理配置信息\n {}",configInfo);
        Map<String,Map> config = JSONObject.parseObject(configInfo, HashMap.class);
        clearCache();
        try {
            RpcProxyCache.PARAM_TYPE.putAll(config.get("paramType"));
            RpcProxyCache.SERVER_TYPE.putAll(config.get("serverType"));
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    private void clearCache() {
        RpcProxyCache.PARAM_TYPE.clear();
        RpcProxyCache.SERVER_TYPE.clear();
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }
}
