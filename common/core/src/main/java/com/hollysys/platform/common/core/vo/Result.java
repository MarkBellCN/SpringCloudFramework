package com.hollysys.platform.common.core.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hollysys.platform.common.core.exception.BaseException;
import com.hollysys.platform.common.core.exception.ErrorType;
import com.hollysys.platform.common.core.exception.SystemErrorType;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class Result<T> implements Serializable {
    public static final String SUCCESSFUL_CODE = "1";
    public static final String SUCCESSFUL_MSG = "success";

    private String code;

    private String msg;

    private T data;

    public Result() {
    }

    public Result(ErrorType errorType) {
        this.code = errorType.getCode();
        this.msg = errorType.getMsg();
    }

    private Result(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Result(ErrorType errorType, T data) {
        this(errorType);
        this.data = data;
    }

    public static Result success(Object data) {
        return new Result<>(SUCCESSFUL_CODE, SUCCESSFUL_MSG, data);
    }

    public static Result success() {
        return success(null);
    }

    public static Result fail() {
        return new Result(SystemErrorType.SYSTEM_ERROR);
    }

    public static Result fail(BaseException baseException, Object data) {
        return new Result<>(baseException.getErrorType(), data);
    }

    public static Result fail(ErrorType errorType, Object data) {
        return new Result<>(errorType, data);
    }

    public static Result fail(ErrorType errorType) {
        return Result.fail(errorType, null);
    }

    public static Result fail(Object data) {
        return new Result<>(SystemErrorType.SYSTEM_ERROR, data);
    }

    @JsonIgnore
    public boolean isSuccess() {
        return SUCCESSFUL_CODE.equals(this.code);
    }

    @JsonIgnore
    public boolean isFail() {
        return !isSuccess();
    }
}
