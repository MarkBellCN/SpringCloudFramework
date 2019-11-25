package com.hollysys.platform.auth.server.exception;

import com.hollysys.platform.common.core.exception.ErrorType;
import com.hollysys.platform.common.core.utils.LocaleMessage;
import com.hollysys.platform.common.core.utils.SpringContextUtil;
import lombok.Getter;

@Getter
public enum AuthErrorType implements ErrorType {
    INVALID_REQUEST("040001"),
    INVALID_CLIENT("040002"),
    INVALID_GRANT("040003"),
    INVALID_SCOPE("040004"),
    INVALID_TOKEN("040005"),
    INSUFFICIENT_SCOPE("040010"),
    REDIRECT_URI_MISMATCH("040020"),
    ACCESS_DENIED("040030"),
    METHOD_NOT_ALLOWED("040040"),
    SERVER_ERROR("040050"),
    UNAUTHORIZED_CLIENT("040060"),
    UNAUTHORIZED("040061"),
    UNSUPPORTED_RESPONSE_TYPE("040070"),
    UNSUPPORTED_GRANT_TYPE("040071");

    /**
     * 错误类型码
     */
    private String code;
    /**
     * 错误类型描述信息
     */
    private String msg;

    AuthErrorType(String code) {
        this.code = code;
        this.msg = localeMessage.getMessage(code);
    }
}
