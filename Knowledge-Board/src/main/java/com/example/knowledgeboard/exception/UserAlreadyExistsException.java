package com.example.knowledgeboard.exception;

import com.example.knowledgeboard.error.BusinessException;
import com.example.knowledgeboard.error.ErrorCode;

public class UserAlreadyExistsException extends BusinessException {

    public UserAlreadyExistsException() {
        super(ErrorCode.USER_ALREADY_EXISTS);
    }

}