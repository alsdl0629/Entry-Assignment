package com.example.knowledgeboard.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {


    INVALID_TOKEN(401, "Invalid Token"),
    EXPIRED_TOKEN(401, "Expired_Token"),

    INVALID_PASSWORD(401, "Invalid Password"),
    FORBIDDEN(403, "Forbidden"),
    USER_NOT_FOUND(404, "User Not Found"),
    AUTHENTICATION_NOT_FOUND(404, "Authentication Not Found"),
    USER_ALREADY_EXISTS(409, "User Already Exists"),

    BOARD_NOT_FOUND(404, "Board Not Found");


    private final int status;

    private final String message;

}
