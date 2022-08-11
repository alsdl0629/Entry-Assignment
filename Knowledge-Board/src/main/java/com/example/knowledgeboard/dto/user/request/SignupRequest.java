package com.example.knowledgeboard.dto.user.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@AllArgsConstructor
@Getter
public class SignupRequest {


    @NotBlank(message = "name은 필수 항목입니다.")
    @Size(max = 10, message = "name은 10글자 이하여야 합니다.")
    private String name;

    @NotBlank(message = "account_id는 필수 항목입니다.")
    @Size(min = 5 ,max = 20, message = "account_id는 5글자 이상, 20글자 이하여야 합니다.")
    private String accountId;

    @NotBlank(message = "introduction은 필수 항목입니다.")
    @Size(max = 30, message = "introduction은 30글자 이하여야 합니다.")
    private String introduction;

    @NotBlank(message = "password는 필수 항목입니다.")
    @Size(min = 8, max = 20, message = "password는 8글자 이상, 20글자 이하여야 합니다.")
    private String password;

}