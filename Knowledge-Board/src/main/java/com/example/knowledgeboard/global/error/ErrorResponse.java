package com.example.knowledgeboard.global.error;

import lombok.*;

@Getter
@AllArgsConstructor
@Builder
public class ErrorResponse {

    private int status;

    private String message;
}
