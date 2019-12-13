package com.hollysys.platform.rpc.proxy.server.config;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executor;

@Slf4j
@Component
public class DynamicProxyConfig {
    private static final String DATA_ID = "gateway-router.json";
    private static final String GROUP = "DEFAULT_GROUP";

    @Value("${spring.cloud.nacos.config.server-addr}")
    private String serverAddr;

    @Bean
    public void refreshRouting() throws NacosException {
        ConfigService configService = NacosFactory.createConfigService(serverAddr);
        String configInfo = configService.getConfig(DATA_ID, GROUP, 5000);
        configService.addListener(DATA_ID, GROUP, new Listener() {
            @Override
            public Executor getExecutor() {
                return null;
            }

            @Override
            public void receiveConfigInfo(String configInfo) {
            }
        });
    }
}
