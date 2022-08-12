package com.example.knowledgeboard.exception;

import com.example.knowledgeboard.error.BusinessException;
import com.example.knowledgeboard.error.ErrorCode;

public class UserForbiddenException extends BusinessException {

    public static BusinessException EXCEPTION =
            new UserForbiddenException();

    private UserForbiddenException() {
        super(ErrorCode.USER_FORBIDDEN);
    }
}
