package com.hollysys.platform.auth.api.service;

import com.hollysys.platform.common.core.vo.Result;
import org.springframework.security.jwt.Jwt;

public interface AuthService {
    public static final String X_CLIENT_TOKEN_USER = "x-client-token-user";

    public static final String X_CLIENT_TOKEN = "x-client-token";
    /**
     * 调用签权服务，判断用户是否有权限
     */
    Result authenticate(String authentication, String url, String method);

    /**
     * 判断url是否在忽略的范围内
     * 只要是配置中的开头，即返回true
     */
    boolean ignoreAuthentication(String url);

    /**
     * 查看签权服务器返回结果，有权限返回true
     */
    boolean hasPermission(Result authResult);

    /**
     * 是否无效authentication
     *
     * @param authentication
     * @return
     */
    boolean invalidJwtAccessToken(String authentication);

    /**
     * 调用签权服务，判断用户是否有权限
     */
    boolean hasPermission(String authentication, String url, String method);

    /**
     * 从认证信息中提取jwt token 对象
     */
    Jwt getJwt(String authentication);
}
