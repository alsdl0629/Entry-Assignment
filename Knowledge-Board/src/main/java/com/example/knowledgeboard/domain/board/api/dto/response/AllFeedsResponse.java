package com.example.knowledgeboard.domain.board.api.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Builder
public class AllFeedsResponse {

    private String writer;

    private String title;

    private LocalDateTime createdAt;

    private Integer views;

    private Integer likeCounts;

    private Integer commentCounts;

}
