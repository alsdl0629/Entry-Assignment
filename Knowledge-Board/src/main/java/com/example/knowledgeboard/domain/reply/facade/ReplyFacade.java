package com.example.knowledgeboard.domain.reply.facade;

import com.example.knowledgeboard.domain.comment.entiry.Comment;
import com.example.knowledgeboard.domain.reply.api.dto.response.ReplyResponse;
import com.example.knowledgeboard.domain.reply.entiry.Reply;
import com.example.knowledgeboard.domain.reply.exception.ReplyNotFoundException;
import com.example.knowledgeboard.domain.reply.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class ReplyFacade {

    private final ReplyRepository replyRepository;

    public List<ReplyResponse> getReplies(Comment comment) {
        return comment.getReplies()
                .stream().map(reply -> ReplyResponse.builder()
                        .replyId(reply.getId())
                        .userId(reply.getUser().getId())
                        .replyWriter(reply.getUser().getAccountId())
                        .content(reply.getContent())
                        .createdDateReply(reply.getCreatedDate())
                        .build())
                .collect(Collectors.toList());
    }

    public Reply getByReplyId(Integer replyId) {
        return replyRepository.findById(replyId)
                .orElseThrow(() -> ReplyNotFoundException.EXCEPTION);
    }

}
