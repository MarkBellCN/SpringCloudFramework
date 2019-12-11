package com.hollysys.platform.auth.server.exception;

import com.hollysys.platform.common.core.vo.Result;
import com.hollysys.platform.common.web.exception.DefaultGlobalExceptionHandlerAdvice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandlerAdvice extends DefaultGlobalExceptionHandlerAdvice {
    @ExceptionHandler(value = {UsernameNotFoundException.class})
    public Result usernameNotFoundException(UsernameNotFoundException ex) {
        return Result.fail(AuthErrorType.USERNAME_NOT_FOUND);
    }
}