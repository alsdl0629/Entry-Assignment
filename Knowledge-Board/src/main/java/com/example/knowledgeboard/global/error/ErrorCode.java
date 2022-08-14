package com.example.knowledgeboard.global.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {


    INVALID_TOKEN(401, "Invalid Token"),
    EXPIRED_TOKEN(401, "Expired_Token"),
    INVALID_PASSWORD(401, "Invalid Password"),
    REFRESH_TOKEN_NOT_FOUND(404, "Refresh Token Not Found"),

    USER_FORBIDDEN(403, "User Forbidden"),
    USER_NOT_FOUND(404, "User Not Found"),
    AUTHENTICATION_NOT_FOUND(404, "Authentication Not Found"),
    USER_ALREADY_EXISTS(409, "User Already Exists"),

    FEED_NOT_FOUND(404, "Feed Not Found"),

    COMMENT_NOT_FOUND(404, "Comment Not Found"),

    REPLY_NOT_FOUND(404, "Reply Not Found");


    private final int status;

    private final String message;

}
