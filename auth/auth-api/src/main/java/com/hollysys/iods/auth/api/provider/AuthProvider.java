package com.hollysys.iods.auth.api.provider;

import com.hollysys.iods.common.core.vo.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
@Component
@FeignClient(name = "auth-server", fallback = AuthProvider.AuthProviderFallback.class)
public interface AuthProvider {
    /**
     * 调用签权服务，判断用户是否有权限
     */
    @PostMapping(value = "/auth/permission")
    Result auth(@RequestHeader(HttpHeaders.AUTHORIZATION) String authentication, @RequestParam("url") String url, @RequestParam("method") String method);

    @Component
    class AuthProviderFallback implements AuthProvider {
        /**
         * 降级统一返回无权限
         */
        @Override
        public Result auth(String authentication, String url, String method) {
            return Result.fail();
        }
    }
}

