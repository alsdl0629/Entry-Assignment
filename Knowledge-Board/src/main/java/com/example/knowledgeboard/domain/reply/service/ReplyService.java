package com.example.knowledgeboard.domain.reply.service;

import com.example.knowledgeboard.domain.comment.entiry.Comment;
import com.example.knowledgeboard.domain.comment.facade.CommentFacade;
import com.example.knowledgeboard.domain.reply.api.dto.request.CreateReplyRequest;
import com.example.knowledgeboard.domain.reply.api.dto.request.UpdateReplyRequest;
import com.example.knowledgeboard.domain.reply.entiry.Reply;
import com.example.knowledgeboard.domain.reply.facade.ReplyFacade;
import com.example.knowledgeboard.domain.reply.repository.ReplyRepository;
import com.example.knowledgeboard.domain.user.entity.User;
import com.example.knowledgeboard.domain.user.exception.UserForbiddenException;
import com.example.knowledgeboard.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class ReplyService {

    private final ReplyRepository replyRepository;
    private final UserFacade userFacade;
    private final CommentFacade commentFacade;
    private final ReplyFacade replyFacade;

    public void addReply(Integer commentId, CreateReplyRequest request) {

        User user = userFacade.getUser();

        Comment comment = commentFacade.getByCommentId(commentId);

        replyRepository.save(Reply.builder()
                .content(request.getContent())
                .user(user)
                .comment(comment)
                .build());
    }

    public void updateReply(Integer replyId, UpdateReplyRequest request) {

        Reply reply = replyFacade.getByReplyId(replyId);
        checkUser(reply);

        reply.updateReply(request.getContent());
    }

    public void removeReply(Integer replyId) {

        Reply reply = replyFacade.getByReplyId(replyId);
        checkUser(reply);

        replyRepository.delete(reply);
    }

    private void checkUser(Reply reply) {
        User user = userFacade.getUser();

        if(!reply.getUser().equals(user)) {
            throw UserForbiddenException.EXCEPTION;
        }
    }

}
