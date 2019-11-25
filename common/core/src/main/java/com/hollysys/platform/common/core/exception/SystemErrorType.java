package com.hollysys.platform.common.core.exception;

import lombok.Getter;

@Getter
public enum SystemErrorType implements ErrorType {
    SYSTEM_ERROR("-1"),
    SYSTEM_BUSY("000001"),

    GATEWAY_NOT_FOUND_SERVICE("010404"),
    GATEWAY_ERROR("010500"),
    GATEWAY_CONNECT_TIME_OUT("010002"),

    ARGUMENT_NOT_VALID("020000"),
    UPLOAD_FILE_SIZE_LIMIT("020001");

    /**
     * 错误类型码
     */
    private String code;
    /**
     * 错误类型描述信息
     */
    private String msg;

    SystemErrorType(String code) {
        this.code = code;
        this.msg = localeMessage.getMessage(code);
    }
}
