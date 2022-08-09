package com.example.knowledgeboard.exception;

import com.example.knowledgeboard.error.BusinessException;
import com.example.knowledgeboard.error.ErrorCode;

public class UserNotFoundException extends BusinessException {

    public static BusinessException EXCEPTION =
            new UserNotFoundException();
    private UserNotFoundException() {
        super(ErrorCode.USER_NOT_FOUND);
    }
}
