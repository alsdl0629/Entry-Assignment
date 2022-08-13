package com.example.knowledgeboard.domain.board.api.dto.response;

import com.example.knowledgeboard.domain.comment.api.dto.response.CommentResponse;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Getter
@Builder
public class DetailedFeedResponse {

    private Integer boardId;

    private Integer userId;

    private String writer;

    private String title;

    private String content;

    private LocalDateTime createdAt;

    private Integer views;

    private boolean liked;

    private Integer likeCounts;

    private Integer commentCounts;

    private List<CommentResponse> comments;

}
