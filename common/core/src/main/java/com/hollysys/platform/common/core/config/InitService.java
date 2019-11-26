package com.hollysys.platform.common.core.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class InitService implements InitializingBean {
    @Override
    public void afterPropertiesSet() throws Exception {
        System.setProperty("dubbo.application.logger","slf4j");
    }
}
