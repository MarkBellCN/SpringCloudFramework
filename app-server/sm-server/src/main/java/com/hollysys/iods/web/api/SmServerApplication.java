package com.hollysys.iods.web.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SmServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(SmServerApplication.class, args);
    }
}
