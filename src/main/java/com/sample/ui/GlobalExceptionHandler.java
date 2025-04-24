package com.sample.ui;

import com.sample.common.domain.exception.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.sample.common.domain.exception.ErrorCode.INVALID_INPUT_VALUE;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public Response<Void> handleIllegalAccessException(IllegalArgumentException e) {
        return Response.error(INVALID_INPUT_VALUE);
    }

    @ExceptionHandler(Exception.class)
    public Response<Void> handleException(Exception e) {
        log.error(e.getMessage());
        return Response.error(ErrorCode.INTERNAL_SERVER_ERROR);
    }
}
