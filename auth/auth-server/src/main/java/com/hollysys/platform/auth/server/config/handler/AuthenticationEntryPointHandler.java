package com.hollysys.platform.auth.server.config.handler;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.hollysys.platform.auth.server.exception.AuthErrorType;
import com.hollysys.platform.common.core.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.ExceptionTranslationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

@Component
public class AuthenticationEntryPointHandler implements AuthenticationEntryPoint, Serializable {
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        response.setContentType("application/json");
        if(e instanceof InsufficientAuthenticationException){
            objectMapper.writeValue(response.getOutputStream(), Result.fail(AuthErrorType.ACCESS_DENIED));
        }else{
            objectMapper.writeValue(response.getOutputStream(), Result.fail(e.getMessage()));
        }
    }
}
