package com.hollysys.platform.common.web.config;

import com.hollysys.platform.common.web.interceptor.UserInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@Configuration
public class WebServerMvcConfigurerAdapter implements WebMvcConfigurer {
    @Resource
    private MessageSource messageSource;

    @Bean
    public HandlerInterceptor userInterceptor() {
        return new UserInterceptor();
    }

    @Bean
    public Validator validator() {
        LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
        validator.setValidationMessageSource(messageSource);
        return validator;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(userInterceptor()).addPathPatterns("/**");
    }

    @Override
    public Validator getValidator() {
        return validator();
    }
}
