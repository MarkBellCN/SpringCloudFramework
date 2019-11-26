package com.hollysys.platform.auth.server.exception;

import com.hollysys.platform.common.core.exception.ErrorType;
import com.hollysys.platform.common.core.utils.LocaleMessage;
import com.hollysys.platform.common.core.utils.SpringContextUtil;
import lombok.Getter;

@Getter
public enum AuthErrorType implements ErrorType {
    INVALID_REQUEST("00020001","invalid.request"),
    INVALID_CLIENT("00020002","invalid.client"),
    INVALID_GRANT("00020003","invalid.grant"),
    INVALID_SCOPE("00020004","invalid.scope"),
    INVALID_TOKEN("00020005","invalid.token"),
    INSUFFICIENT_SCOPE("00020006","insufficient.scope"),
    REDIRECT_URI_MISMATCH("00020007","redirect.uri.mismatch"),
    ACCESS_DENIED("00020008","access.denied"),
    METHOD_NOT_ALLOWED("00020009","method.not.allowed"),
    SERVER_ERROR("00020010","server.error"),
    UNAUTHORIZED_CLIENT("00020011","unauthorized.client"),
    UNAUTHORIZED("00020012","unauthorized"),
    UNSUPPORTED_RESPONSE_TYPE("00020013","unsupported.response.type"),
    UNSUPPORTED_GRANT_TYPE("00020014","unsupported.grant.type");

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
