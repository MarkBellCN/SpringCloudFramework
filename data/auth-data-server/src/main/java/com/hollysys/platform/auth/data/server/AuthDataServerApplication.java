package com.hollysys.platform.auth.data.server;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.hollysys.platform.auth.data.*.mapper")
public class AuthDataServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthDataServerApplication.class, args);
    }
}
