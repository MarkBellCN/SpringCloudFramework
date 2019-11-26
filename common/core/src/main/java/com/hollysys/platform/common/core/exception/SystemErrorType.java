package com.hollysys.platform.common.core.exception;

import lombok.Getter;

@Getter
public enum SystemErrorType implements ErrorType {
    SYSTEM_ERROR("00000000","system.error"),
    SYSTEM_BUSY("00000001","system.busy"),

    ARGUMENT_NOT_VALID("00000101","argument.not.valid"),
    UPLOAD_FILE_SIZE_LIMIT("00000102","upload.file.size.limit"),

    GATEWAY_NOT_FOUND_SERVICE("00010001","gateway.not.found.service"),
    GATEWAY_ERROR("00010002","gateway.error"),
    GATEWAY_CONNECT_TIME_OUT("00010003","gateway.connect.time.out");



    /**
     * 错误类型码
     */
    private String code;
    /**
     * 错误类型描述信息
     */
    private String msg;

    SystemErrorType(String code,String msg) {
        this.code = code;
        this.msg = localeMessage.getMessage(msg);
    }
}
