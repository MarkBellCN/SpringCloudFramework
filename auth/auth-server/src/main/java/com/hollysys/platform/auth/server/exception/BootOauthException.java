package com.hollysys.platform.auth.server.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hollysys.platform.common.core.vo.Result;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

@EqualsAndHashCode(callSuper = true)
@Data
@JsonSerialize(using = BootOauthExceptionSerializer.class)
public class BootOauthException extends OAuth2Exception {
    private final Result result;

    BootOauthException(OAuth2Exception oAuth2Exception) {
        super(oAuth2Exception.getSummary(), oAuth2Exception);
        this.result = Result.fail(AuthErrorType.valueOf(oAuth2Exception.getOAuth2ErrorCode().toUpperCase()), oAuth2Exception);
    }
}