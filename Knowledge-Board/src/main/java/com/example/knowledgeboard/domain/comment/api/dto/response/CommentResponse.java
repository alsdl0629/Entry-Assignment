package com.example.knowledgeboard.domain.comment.api.dto.response;

import com.example.knowledgeboard.domain.reply.api.dto.response.ReplyResponse;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Getter
@Builder
public class CommentResponse {

    private Integer commentId;

    private Integer userId;

    private String commentWriter;

    private String content;

    private LocalDate createdDate;

    private Integer replyCounts;

    private List<ReplyResponse> replies;

}
