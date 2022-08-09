package com.example.knowledgeboard.exception;

import com.example.knowledgeboard.error.BusinessException;
import com.example.knowledgeboard.error.ErrorCode;

public class FeedNotFoundException extends BusinessException {

    public static BusinessException EXCEPTION =
            new FeedNotFoundException();

    private FeedNotFoundException() {
        super(ErrorCode.FEED_NOT_FOUND);
    }

}
