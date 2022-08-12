package com.example.knowledgeboard.dto.board.response;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Builder
public class DetailedFeedResponse {

    private Integer boardId;

    private String writer;

    private String title;

    private String content;

    private LocalDateTime createdAt;

    private Integer views;

    private boolean liked;

    private Integer likeCounts;

}
