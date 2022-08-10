package com.example.knowledgeboard.dto.board.response;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Builder
public class DetailedFeedResponse {

    private Integer boardId;

    private String title;

    private String content;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private Integer views;

    private String writer;

}
