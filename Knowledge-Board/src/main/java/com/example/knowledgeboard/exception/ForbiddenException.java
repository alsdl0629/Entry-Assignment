package com.example.knowledgeboard.exception;

import com.example.knowledgeboard.error.BusinessException;
import com.example.knowledgeboard.error.ErrorCode;

public class ForbiddenException extends BusinessException {

    public static BusinessException EXCEPTION =
            new ForbiddenException();

    private ForbiddenException() {
        super(ErrorCode.FORBIDDEN);
    }
}
