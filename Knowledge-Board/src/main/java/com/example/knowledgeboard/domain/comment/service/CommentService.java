package com.example.knowledgeboard.domain.comment.service;

import com.example.knowledgeboard.domain.comment.api.dto.request.CreateCommentRequest;
import com.example.knowledgeboard.domain.board.entiry.Board;
import com.example.knowledgeboard.domain.board.repository.BoardRepository;
import com.example.knowledgeboard.domain.comment.api.dto.request.UpdateCommentRequest;
import com.example.knowledgeboard.domain.comment.facade.CommentFacade;
import com.example.knowledgeboard.domain.comment.entiry.Comment;
import com.example.knowledgeboard.domain.comment.repository.CommentRepository;
import com.example.knowledgeboard.domain.user.entity.User;
import com.example.knowledgeboard.domain.board.exception.FeedNotFoundException;
import com.example.knowledgeboard.domain.user.exception.UserForbiddenException;
import com.example.knowledgeboard.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Transactional
@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;
    private final UserFacade userFacade;
    private final CommentFacade commentFacade;

    public void addComment(Integer feedId, CreateCommentRequest request) {

        User user = userFacade.getUser();

        Board board = boardRepository.findById(feedId)
                .orElseThrow(() -> FeedNotFoundException.EXCEPTION);

        commentRepository.save(Comment.builder()
                .content(request.getContent())
                .user(user)
                .board(board)
                .build());
    }

    public void updateComment(Integer commentId, UpdateCommentRequest request) {

        Comment comment = commentFacade.getByCommentId(commentId);
        checkUser(comment);

        comment.updateComment(request.getContent());
    }

    public void removeComment(Integer commentId) {

        Comment comment = commentFacade.getByCommentId(commentId);
        checkUser(comment);

        commentRepository.delete(comment);
    }

    private void checkUser(Comment comment) {
        User user = userFacade.getUser();

        if(!comment.getUser().equals(user)) {
            throw UserForbiddenException.EXCEPTION;
        }
    }


}
