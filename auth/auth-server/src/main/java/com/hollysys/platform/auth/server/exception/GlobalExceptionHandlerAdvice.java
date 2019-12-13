package com.hollysys.platform.auth.server.exception;

import com.hollysys.platform.common.core.vo.Result;
import com.hollysys.platform.common.web.exception.DefaultGlobalExceptionHandlerAdvice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandlerAdvice extends DefaultGlobalExceptionHandlerAdvice {
    @ExceptionHandler(value = {AccessDeniedException.class})
    public Result accessDeniedException(AccessDeniedException e) {
        return Result.fail(AuthErrorType.ACCESS_DENIED);
    }

}