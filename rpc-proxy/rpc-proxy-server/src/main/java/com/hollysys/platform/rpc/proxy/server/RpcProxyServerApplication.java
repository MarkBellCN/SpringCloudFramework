package com.hollysys.platform.rpc.proxy.server;

import com.hollysys.platform.rpc.proxy.server.config.ThriftServeConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
@EnableDiscoveryClient
public class RpcProxyServerApplication {
    private static ThriftServeConfig thriftServer;

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(RpcProxyServerApplication.class, args);
        try {
            thriftServer = context.getBean(ThriftServeConfig.class);
            thriftServer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
