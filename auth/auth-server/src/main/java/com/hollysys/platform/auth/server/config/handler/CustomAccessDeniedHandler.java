package com.hollysys.platform.auth.server.config.handler;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.hollysys.platform.common.core.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
        response.setContentType("application/json");
        objectMapper.writeValue(response.getOutputStream(), Result.fail(e.getMessage()));
    }
}
