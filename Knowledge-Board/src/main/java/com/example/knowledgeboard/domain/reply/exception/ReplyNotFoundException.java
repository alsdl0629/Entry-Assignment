package com.example.knowledgeboard.domain.reply.exception;

import com.example.knowledgeboard.global.error.ErrorCode;
import com.example.knowledgeboard.global.error.exception.BusinessException;

public class ReplyNotFoundException extends BusinessException {

    public static BusinessException EXCEPTION =
            new ReplyNotFoundException();

    private ReplyNotFoundException() {
        super(ErrorCode.REPLY_NOT_FOUND);
    }

}
