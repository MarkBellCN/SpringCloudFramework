package com.hollysys.iods.web.api.exception;

import com.hollysys.platform.common.core.exception.ErrorType;
import lombok.Getter;

@Getter
public enum SmServerErrorType implements ErrorType {
    SAVE_DATA_ERROR("00030001","save.data.error");

    /**
     * 错误类型码
     */
    private String code;
    /**
     * 错误类型描述信息
     */
    private String msg;

    SmServerErrorType(String code,String msg) {
        this.code = code;
        this.msg = localeMessage.getMessage(msg);
    }
}
