package com.hollysys.platform.auth.server.config;

import com.hollysys.platform.auth.server.config.handler.AuthenticationEntryPointHandler;
import com.hollysys.platform.auth.server.config.handler.CustomAccessDeniedHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
    @Autowired
    private CustomAccessDeniedHandler customAccessDeniedHandler;

    @Autowired
    private AuthenticationEntryPointHandler unauthorizedHandler;

    @Autowired
    private TokenStore tokenStore;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources
                .authenticationEntryPoint(unauthorizedHandler)
                .accessDeniedHandler(customAccessDeniedHandler)
                .tokenStore(tokenStore)
                .resourceId("resourceId");
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {

        http
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and()
                .authorizeRequests()
                .anyRequest()
                .authenticated();
    }
}
