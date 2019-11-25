package com.hollysys.iods.web.api.exception;

import com.hollysys.platform.common.core.exception.ErrorType;
import lombok.Getter;

@Getter
public enum SmServerErrorType implements ErrorType {
    INVALID_REQUEST("050001");

    /**
     * 错误类型码
     */
    private String code;
    /**
     * 错误类型描述信息
     */
    private String msg;

    SmServerErrorType(String code) {
        this.code = code;
        this.msg = localeMessage.getMessage(code);
    }
}
