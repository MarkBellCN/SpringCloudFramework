package com.hollysys.iods.auth.authorization.server.config.redis;

import com.hollysys.iods.auth.api.config.RedisObjectSerializer;
import com.hollysys.iods.data.api.entity.SysResources;
import com.hollysys.iods.data.api.entity.SysRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisAuthConfiguration extends CachingConfigurerSupport {

    @Bean
    public RedisTemplate<String, SysRole> roleTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, SysRole> template = new RedisTemplate();
        template.setConnectionFactory(factory);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new RedisObjectSerializer());
        return template;
    }

    @Bean
    public RedisTemplate<String, SysResources> resourcesTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, SysResources> template = new RedisTemplate();
        template.setConnectionFactory(factory);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new RedisObjectSerializer());
        return template;
    }
}
