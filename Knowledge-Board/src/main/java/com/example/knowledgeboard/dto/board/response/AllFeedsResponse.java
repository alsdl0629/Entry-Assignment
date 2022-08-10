package com.example.knowledgeboard.dto.board.response;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Builder
public class AllFeedsResponse {

    private String title;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private Integer views;

    private String writer;

}
