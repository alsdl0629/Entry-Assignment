package com.example.knowledgeboard.domain.comment.api.dto.response;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Builder
public class CommentResponse {

    private Integer commentId;

    private Integer userId;

    private String writer;

    private String content;

    private LocalDate createdAt;

}
