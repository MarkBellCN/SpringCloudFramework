package com.hollysys.platform.auth.server.config;


import com.hollysys.platform.auth.server.exception.CustomWebResponseExceptionTranslator;
import com.hollysys.platform.auth.server.oauth2.CustomTokenEnhancer;
import com.hollysys.platform.auth.server.service.UsernameUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.CompositeTokenGranter;
import org.springframework.security.oauth2.provider.TokenGranter;
import org.springframework.security.oauth2.provider.TokenRequest;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.approval.ApprovalStoreUserApprovalHandler;
import org.springframework.security.oauth2.provider.approval.JdbcApprovalStore;
import org.springframework.security.oauth2.provider.approval.UserApprovalHandler;
import org.springframework.security.oauth2.provider.client.ClientCredentialsTokenGranter;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeTokenGranter;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.implicit.ImplicitTokenGranter;
import org.springframework.security.oauth2.provider.password.ResourceOwnerPasswordTokenGranter;
import org.springframework.security.oauth2.provider.refresh.RefreshTokenGranter;
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestFactory;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private UsernameUserDetailService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private ClientDetailsService clientDetailsService;

    @Resource
    private DataSource dataSource;


    @Bean("jdbcClientDetailsService")
    public JdbcClientDetailsService getJdbcClientDetailsService() {
        return new JdbcClientDetailsService(dataSource);
    }


    @Value("${spring.security.oauth2.jwt.signingKey}")
    private String signingKey;

    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) {
        oauthServer
                .allowFormAuthenticationForClients()
                // 开启/oauth/token_key验证端口无权限访问
                .tokenKeyAccess("permitAll()")
                // 开启/oauth/check_token验证端口认证权限访问
                .checkTokenAccess("isAuthenticated()");
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        // 使用JdbcClientDetailsService客户端详情服务
        clients.withClientDetails(getJdbcClientDetailsService());
    }


    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints.authenticationManager(authenticationManager)
                .authorizationCodeServices(authorizationCodeServices())
                .approvalStore(approvalStore())
                .tokenStore(tokenStore())
                .tokenGranter(tokenGranter())
                .exceptionTranslator(customExceptionTranslator())
                .reuseRefreshTokens(false)
                .authenticationManager(authenticationManager)
                .userApprovalHandler(userApprovalHandler())
                .userDetailsService(userDetailsService);
    }

    @Bean
    public ApprovalStore approvalStore() {
        return new JdbcApprovalStore(dataSource);
    }

    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    @Bean
    public UserApprovalHandler userApprovalHandler() {
        ApprovalStoreUserApprovalHandler userApprovalHandler = new ApprovalStoreUserApprovalHandler();
        userApprovalHandler.setApprovalStore(approvalStore());
        userApprovalHandler.setClientDetailsService(clientDetailsService);
        userApprovalHandler.setRequestFactory(oAuth2RequestFactory());
        return userApprovalHandler;
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey(signingKey);
        return converter;
    }

    @Bean
    public WebResponseExceptionTranslator customExceptionTranslator() {
        return new CustomWebResponseExceptionTranslator();
    }

    @Bean
    public JwtTokenStore jwtTokenStore() {
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    @Bean
    protected AuthorizationCodeServices authorizationCodeServices() {
        // 授权码存储等处理方式类，使用jdbc，操作oauth_code表
        return new JdbcAuthorizationCodeServices(dataSource);
    }

    @Bean
    public TokenEnhancerChain tokenEnhancerChain() {
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(Arrays.asList(new CustomTokenEnhancer(), jwtAccessTokenConverter()));
        return tokenEnhancerChain;
    }

    @Bean
    public DefaultTokenServices authorizationServerTokenServices() {
        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenStore(tokenStore());
        defaultTokenServices.setSupportRefreshToken(true);
        //token 有效时间,默认12h
        defaultTokenServices.setClientDetailsService(clientDetailsService);
        // 如果没有设置它,JWT就失效了.
        defaultTokenServices.setTokenEnhancer(tokenEnhancerChain());
        return defaultTokenServices;
    }

    @Bean
    public DefaultOAuth2RequestFactory oAuth2RequestFactory() {
        return new DefaultOAuth2RequestFactory(clientDetailsService);
    }

    private TokenGranter tokenGranter() {
        return new TokenGranter() {
            private CompositeTokenGranter delegate;
            @Override
            public OAuth2AccessToken grant(String grantType, TokenRequest tokenRequest) {
                if (delegate == null) {
                    delegate = new CompositeTokenGranter(getDefaultTokenGranters());
                }
                return delegate.grant(grantType, tokenRequest);
            }
        };
    }

    private List<TokenGranter> getDefaultTokenGranters() {
        List<TokenGranter> tokenGranters = new ArrayList<>();
        tokenGranters.add(new AuthorizationCodeTokenGranter(authorizationServerTokenServices(),
                authorizationCodeServices(), clientDetailsService, oAuth2RequestFactory()));
        tokenGranters.add(new RefreshTokenGranter(authorizationServerTokenServices(), clientDetailsService,
                oAuth2RequestFactory()));
        ImplicitTokenGranter implicit = new ImplicitTokenGranter(authorizationServerTokenServices(),
                clientDetailsService, oAuth2RequestFactory());
        tokenGranters.add(implicit);
        tokenGranters.add(new ClientCredentialsTokenGranter(authorizationServerTokenServices(), clientDetailsService,
                oAuth2RequestFactory()));
        if (authenticationManager != null) {
            tokenGranters.add(new ResourceOwnerPasswordTokenGranter(authenticationManager,
                    authorizationServerTokenServices(), clientDetailsService, oAuth2RequestFactory()));
        }
        return tokenGranters;
    }

}
