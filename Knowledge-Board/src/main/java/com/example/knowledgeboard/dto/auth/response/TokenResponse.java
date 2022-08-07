package com.example.knowledgeboard.dto.auth.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;


@AllArgsConstructor
@Getter
@Builder
public class TokenResponse {

    private String accessToken;

    private String refreshToken;

}
