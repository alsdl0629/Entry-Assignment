package com.example.knowledgeboard.domain.auth.exception;

import com.example.knowledgeboard.global.error.BusinessException;
import com.example.knowledgeboard.global.error.ErrorCode;

public class InvalidPasswordException extends BusinessException {

    public static BusinessException EXCEPTION =
            new InvalidPasswordException();

    private InvalidPasswordException() {
        super(ErrorCode.INVALID_PASSWORD);
    }
}
