package com.hollysys.iods.web.api;

import com.hollysys.platform.common.web.config.EnableSwaggerPlugins;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableSwaggerPlugins
@EnableDiscoveryClient
public class SmServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(SmServerApplication.class, args);
    }
}
