package com.example.knowledgeboard.domain.user.exception;

import com.example.knowledgeboard.global.error.BusinessException;
import com.example.knowledgeboard.global.error.ErrorCode;

public class UserForbiddenException extends BusinessException {

    public static BusinessException EXCEPTION =
            new UserForbiddenException();

    private UserForbiddenException() {
        super(ErrorCode.USER_FORBIDDEN);
    }
}
