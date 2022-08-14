package com.example.knowledgeboard.domain.reply.api.dto.response;

import lombok.*;

import java.time.LocalDate;


@AllArgsConstructor
@Getter
@Builder
public class ReplyResponse {

    private Integer replyId;

    private Integer userId;

    private String replyWriter;

    private String content;

    private LocalDate createdDateReply;

}
