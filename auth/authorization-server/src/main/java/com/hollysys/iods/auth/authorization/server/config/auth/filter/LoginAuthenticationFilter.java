package com.hollysys.iods.auth.authorization.server.config.auth.filter;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private static final String SPRING_SECURITY_RESTFUL_USERNAME_KEY = "username";
    private static final String SPRING_SECURITY_RESTFUL_PASSWORD_KEY = "password";
    private boolean postOnly = true;
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (postOnly && !request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException(
                    "Authentication method not supported: " + request.getMethod());
        }
        AbstractAuthenticationToken authRequest;
        String principal;
        String credentials;
        principal = obtainParameter(request, SPRING_SECURITY_RESTFUL_USERNAME_KEY);
        credentials = obtainParameter(request, SPRING_SECURITY_RESTFUL_PASSWORD_KEY);
        principal = principal.trim();
        authRequest = new UsernamePasswordAuthenticationToken(principal, credentials);
        // Allow subclasses to set the "details" property
        setDetails(request, authRequest);
        return this.getAuthenticationManager().authenticate(authRequest);
    }

    private void setDetails(HttpServletRequest request,
                            AbstractAuthenticationToken authRequest) {
        authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
    }

    private String obtainParameter(HttpServletRequest request, String parameter) {
        String result =  request.getParameter(parameter);
        return result == null ? "" : result;
    }
}
