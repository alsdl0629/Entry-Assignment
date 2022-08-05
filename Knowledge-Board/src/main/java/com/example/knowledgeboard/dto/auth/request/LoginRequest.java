package com.example.knowledgeboard.dto.auth.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@Getter
public class LoginRequest {

    @NotBlank(message = "account_id는 필수 항목입니다.")
    private String accountId;

    @NotBlank(message = "password는 필수 항목입니다.")
    private String password;

}