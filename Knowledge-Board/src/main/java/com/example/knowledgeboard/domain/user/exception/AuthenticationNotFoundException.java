package com.example.knowledgeboard.domain.user.exception;

import com.example.knowledgeboard.global.error.BusinessException;
import com.example.knowledgeboard.global.error.ErrorCode;

public class AuthenticationNotFoundException extends BusinessException {

    public static BusinessException EXCEPTION =
            new AuthenticationNotFoundException();

    private AuthenticationNotFoundException() {
        super(ErrorCode.AUTHENTICATION_NOT_FOUND);
    }

}
