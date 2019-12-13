package com.hollysys.platform.auth.server.config;


import com.hollysys.platform.auth.server.config.filter.PhoneLoginAuthenticationFilter;
import com.hollysys.platform.auth.server.config.handler.AuthenticationEntryPointHandler;
import com.hollysys.platform.auth.server.config.handler.CustomAccessDeniedHandler;
import com.hollysys.platform.auth.server.config.handler.LoginAuthFailureHandler;
import com.hollysys.platform.auth.server.config.handler.LoginAuthSuccessHandler;
import com.hollysys.platform.auth.server.config.provider.PhoneAuthenticationProvider;
import com.hollysys.platform.auth.server.oauth2.service.PhoneUserDetailService;
import com.hollysys.platform.auth.server.oauth2.service.UsernameUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    // 自动注入UserDetailsService
    @Autowired
    private UsernameUserDetailService usernameUserDetailService;

    @Autowired
    private PhoneUserDetailService phoneUserDetailService;

    @Autowired
    private AuthenticationEntryPointHandler unauthorizedHandler;

    @Autowired
    private CustomAccessDeniedHandler customAccessDeniedHandler;

    @Autowired
    private LoginAuthFailureHandler loginAuthFailureHandler;

    @Autowired
    private LoginAuthSuccessHandler loginAuthSuccessHandler;

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .addFilterBefore(getPhoneLoginAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler)
                .accessDeniedHandler(customAccessDeniedHandler)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/actuator/**").permitAll();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(phoneAuthenticationProvider());
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        // 设置userDetailsService
        provider.setUserDetailsService(usernameUserDetailService);
        // 禁止隐藏用户未找到异常
        provider.setHideUserNotFoundExceptions(false);
        // 使用BCrypt进行密码的hash
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public PhoneAuthenticationProvider phoneAuthenticationProvider(){
        PhoneAuthenticationProvider provider = new PhoneAuthenticationProvider();
        // 设置userDetailsService
        provider.setUserDetailsService(phoneUserDetailService);
        // 禁止隐藏用户未找到异常
        provider.setHideUserNotFoundExceptions(false);
        return provider;
    }

    @Bean
    public PhoneLoginAuthenticationFilter getPhoneLoginAuthenticationFilter() {
        PhoneLoginAuthenticationFilter filter = new PhoneLoginAuthenticationFilter();
        try {
            filter.setAuthenticationManager(this.authenticationManagerBean());
        } catch (Exception e) {
            e.printStackTrace();
        }
        filter.setAuthenticationSuccessHandler(loginAuthSuccessHandler);
        filter.setAuthenticationFailureHandler(loginAuthFailureHandler);
        return filter;
    }

}
