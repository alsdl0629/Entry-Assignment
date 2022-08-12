package com.example.knowledgeboard.domain.comment.exception;

import com.example.knowledgeboard.global.error.BusinessException;
import com.example.knowledgeboard.global.error.ErrorCode;

public class CommentNotFoundException extends BusinessException {

    public static BusinessException EXCEPTION =
            new CommentNotFoundException();

    private CommentNotFoundException() {
        super(ErrorCode.COMMENT_NOT_FOUND);
    }

}
