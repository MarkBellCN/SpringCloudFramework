package com.hollysys.platform.auth.server.exception;

import lombok.Data;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

@Data
public class CustomUserNameNotFoundException extends OAuth2Exception {

    public CustomUserNameNotFoundException(String msg) {
        super(msg);
    }

    public String getOAuth2ErrorCode() {
        return "username_not_found";
    }
}