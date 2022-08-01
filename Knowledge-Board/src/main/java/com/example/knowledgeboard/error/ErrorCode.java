package com.example.knowledgeboard.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    //회원
    INVALID_PASSWORD(401, "Invalid Password"),
    USER_NOT_FOUND(404, "User Not Found"),
    AUTHENTICATION_NOT_FOUND(404, "Authentication Not Found"),
    USER_ALREADY_EXISTS(409, "User Already Exists");


    private final int status;

    private final String message;

}
