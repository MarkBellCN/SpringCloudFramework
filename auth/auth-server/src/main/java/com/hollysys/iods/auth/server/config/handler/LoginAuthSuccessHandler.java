package com.hollysys.iods.auth.server.config.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hollysys.iods.common.core.vo.Result;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.UnapprovedClientAuthenticationException;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class LoginAuthSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Autowired
    private ClientDetailsService clientDetailsService;

    @Autowired
    private AuthorizationServerTokenServices authorizationServerTokenServices;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String type = request.getHeader("Accept");
        if(!type.contains("text/html")){
            String clientId = "app";
            String clientSecret = "app";
            ClientDetails clientDetails = clientDetailsService.loadClientByClientId(clientId);
            if (null == clientDetails) {
                throw new UnapprovedClientAuthenticationException("clientId不存在" + clientId);
            } else if (!StringUtils.equals(clientDetails.getClientSecret(), clientSecret)) {
                throw new UnapprovedClientAuthenticationException("clientSecret不匹配" + clientId);
            }
            TokenRequest tokenRequest = new TokenRequest(MapUtils.EMPTY_MAP, clientId, clientDetails.getScope(), "custom");
            OAuth2Request oAuth2Request = tokenRequest.createOAuth2Request(clientDetails);
            OAuth2Authentication oAuth2Authentication = new OAuth2Authentication(oAuth2Request, authentication);
            OAuth2AccessToken token = authorizationServerTokenServices.createAccessToken(oAuth2Authentication);
            response.setContentType("application/json;charset=UTF-8");
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(response.getOutputStream(), Result.success(token));
        }else {
            super.onAuthenticationSuccess(request, response, authentication);
        }
    }
}