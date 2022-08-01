package com.example.knowledgeboard.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    protected ResponseEntity<ErrorResponse> handlerException(BusinessException e) {
        final ErrorCode errorCode = e.getErrorCode();

        return new ResponseEntity<>(new ErrorResponse(errorCode.getStatus(),
                errorCode.getMessage()), HttpStatus.valueOf(errorCode.getStatus()));
    }
}