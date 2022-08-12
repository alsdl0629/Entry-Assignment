package com.example.knowledgeboard.domain.auth.api.dto.response;

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
