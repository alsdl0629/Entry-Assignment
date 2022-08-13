package com.example.knowledgeboard.domain.user.exception;

import com.example.knowledgeboard.global.error.exception.BusinessException;
import com.example.knowledgeboard.global.error.ErrorCode;

public class UserAlreadyExistsException extends BusinessException {

    public static BusinessException EXCEPTION =
            new UserAlreadyExistsException();

    private UserAlreadyExistsException() {
        super(ErrorCode.USER_ALREADY_EXISTS);
    }

}