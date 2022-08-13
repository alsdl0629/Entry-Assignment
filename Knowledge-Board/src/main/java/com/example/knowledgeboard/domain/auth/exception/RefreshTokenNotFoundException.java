package com.example.knowledgeboard.domain.auth.exception;

import com.example.knowledgeboard.global.error.ErrorCode;
import com.example.knowledgeboard.global.error.exception.BusinessException;

public class RefreshTokenNotFoundException extends BusinessException {

    public static BusinessException EXCEPTION =
            new RefreshTokenNotFoundException();

    private RefreshTokenNotFoundException() {
        super(ErrorCode.REFRESH_TOKEN_NOT_FOUND);
    }

}
