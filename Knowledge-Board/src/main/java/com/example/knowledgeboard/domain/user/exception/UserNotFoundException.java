package com.example.knowledgeboard.domain.user.exception;

import com.example.knowledgeboard.global.error.exception.BusinessException;
import com.example.knowledgeboard.global.error.ErrorCode;

public class UserNotFoundException extends BusinessException {

    public static BusinessException EXCEPTION =
            new UserNotFoundException();
    private UserNotFoundException() {
        super(ErrorCode.USER_NOT_FOUND);
    }
}
