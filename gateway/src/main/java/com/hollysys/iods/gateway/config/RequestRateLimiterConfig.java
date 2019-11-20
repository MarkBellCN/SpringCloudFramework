package com.hollysys.iods.gateway.config;


import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import reactor.core.publisher.Mono;

public class RequestRateLimiterConfig {
    /**
     * ip地址限流
     */
    @Bean
    @Primary
    public KeyResolver remoteAddressKeyResolver() {
        return exchange -> Mono.just(exchange.getRequest().getRemoteAddress().getHostName());
    }

    /**
     * 请求路径限流
     */
    @Bean
    public KeyResolver apiKeyResolver() {
        return exchange -> Mono.just(exchange.getRequest().getPath().value());
    }

    /**
     * username限流
     */
    @Bean
    public KeyResolver userKeyResolver() {
        return exchange -> Mono.just(exchange.getRequest().getQueryParams().getFirst("username"));
    }
}
