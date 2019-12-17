package com.hollysys.platform.auth.server.exception;

import com.hollysys.platform.common.core.exception.ErrorType;
import com.hollysys.platform.common.core.utils.LocaleMessage;
import com.hollysys.platform.common.core.utils.SpringContextUtil;
import lombok.Getter;

@Getter
public enum AuthErrorType implements ErrorType {
    INVALID_REQUEST("30020001","invalid.request"),
    INVALID_CLIENT("30020002","invalid.client"),
    INVALID_GRANT("30020003","invalid.grant"),
    INVALID_SCOPE("30020004","invalid.scope"),
    INVALID_TOKEN("30020005","invalid.token"),
    INSUFFICIENT_SCOPE("30020006","insufficient.scope"),
    REDIRECT_URI_MISMATCH("30020007","redirect.uri.mismatch"),
    ACCESS_DENIED("30020008","access.denied"),
    METHOD_NOT_ALLOWED("30020009","method.not.allowed"),
    SERVER_ERROR("30020010","server.error"),
    UNAUTHORIZED_CLIENT("30020011","unauthorized.client"),
    UNAUTHORIZED("30020012","unauthorized"),
    UNSUPPORTED_RESPONSE_TYPE("30020013","unsupported.response.type"),
    UNSUPPORTED_GRANT_TYPE("30020014","unsupported.grant.type"),
    USERNAME_NOT_FOUND("30020015","username.not.found"),
    ;

    /**
     * 错误类型码
     */
    private String code;
    /**
     * 错误类型描述信息
     */
    private String msg;

    AuthErrorType(String code,String msg) {
        this.code = code;
        this.msg = localeMessage.getMessage(msg);
    }
}
