package com.example.knowledgeboard.dto.auth.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@AllArgsConstructor
@Getter
public class SignupRequest {

    @NotNull
    @Size(max = 20, message = "아이디는 최대 20글자")
    @JsonProperty("account_id")
    private String accountId;

    @NotNull
    @Size(max = 10, message = "이름은 최대 10글자")
    private String name;

    @NotNull
    private Integer age;

    @NotNull
    @Size(min = 8, max = 20, message = "비밀번호는 8~20글자")
    private String password;
}
