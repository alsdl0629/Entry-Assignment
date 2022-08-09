package com.example.knowledgeboard.exception;

import com.example.knowledgeboard.error.BusinessException;
import com.example.knowledgeboard.error.ErrorCode;

public class ExpiredTokenException extends BusinessException {

    public static BusinessException EXCEPTION =
            new ExpiredTokenException();

    private ExpiredTokenException() {
        super(ErrorCode.EXPIRED_TOKEN);
    }

}
