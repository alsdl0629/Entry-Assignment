package com.example.knowledgeboard.global.exception;

import com.example.knowledgeboard.global.error.exception.BusinessException;
import com.example.knowledgeboard.global.error.ErrorCode;

public class InvalidTokenException extends BusinessException {

    public static BusinessException EXCEPTION =
            new InvalidTokenException();

    private InvalidTokenException() {
        super(ErrorCode.INVALID_TOKEN);
    }

}
