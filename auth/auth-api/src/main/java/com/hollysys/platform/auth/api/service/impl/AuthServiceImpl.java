package com.hollysys.platform.auth.api.service.impl;

import com.hollysys.platform.auth.api.provider.AuthProvider;
import com.hollysys.platform.auth.api.service.AuthService;
import com.hollysys.platform.common.core.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.InvalidSignatureException;
import org.springframework.security.jwt.crypto.sign.MacSigner;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Stream;

@Service
@RefreshScope
@Slf4j
public class AuthServiceImpl implements AuthService {
    @Autowired
    private AuthProvider authProvider;

    /**
     * jwt token 密钥，主要用于token解析，签名验证
     */
    @Value("${spring.security.oauth2.jwt.signingKey}")
    private String signingKey;

    /**
     * 不需要网关签权的url配置(/oauth,/open)
     * 默认/oauth开头是不需要的
     */
    @Value("${gate.ignore.authentication.startWith}")
    private String ignoreUrls = "/oauth";
    /**
     * jwt验签
     */
    private MacSigner verifier;

    @Override
    public Result authenticate(String authentication, String url, String method) {
        return authProvider.auth(authentication, url, method);
    }

    @Override
    public boolean ignoreAuthentication(String url) {
        return Stream.of(this.ignoreUrls.split(",")).anyMatch(ignoreUrl -> url.startsWith(StringUtils.trim(ignoreUrl)));
    }

    @Override
    public boolean hasPermission(Result authResult) {
        log.debug("签权结果:{}", authResult.getData());
        return authResult.isSuccess() && (boolean) authResult.getData();
    }

    @Override
    public boolean hasPermission(String authentication, String url, String method) {
        //token是否有效
        if (invalidJwtAccessToken(authentication)) {
            return Boolean.FALSE;
        }
        //从认证服务获取是否有权限
        return hasPermission(authenticate(authentication, url, method));
    }

    @Override
    public boolean invalidJwtAccessToken(String authentication) {
        verifier = Optional.ofNullable(verifier).orElse(new MacSigner(signingKey));
        //是否无效true表示无效
        boolean invalid = Boolean.TRUE;
        try {
            Jwt jwt = getJwt(authentication);
            jwt.verifySignature(verifier);
            invalid = Boolean.FALSE;
        } catch (InvalidSignatureException | IllegalArgumentException ex) {
            log.warn("user token has expired or signature error ");
        }
        return invalid;
    }

    @Override
    public Jwt getJwt(String authentication) {
        return JwtHelper.decode(authentication);
    }
}
