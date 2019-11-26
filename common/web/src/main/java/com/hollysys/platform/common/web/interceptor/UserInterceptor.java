package com.hollysys.platform.common.web.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hollysys.platform.auth.api.service.AuthService;
import com.hollysys.platform.common.web.utils.UserContextHolder;
import org.apache.commons.lang.StringUtils;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
public class UserInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        checkToken(request.getHeader(AuthService.X_CLIENT_TOKEN_USER));
        String userInfoString = StringUtils.defaultIfBlank(request.getHeader(AuthService.X_CLIENT_TOKEN_USER), "{}");
        UserContextHolder.getInstance().setContext(new ObjectMapper().readValue(userInfoString, Map.class));
        return true;
    }

    private void checkToken(String token) {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        UserContextHolder.getInstance().clear();
    }
}
