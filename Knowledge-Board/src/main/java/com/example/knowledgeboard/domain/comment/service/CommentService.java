package com.example.knowledgeboard.domain.comment.service;

import com.example.knowledgeboard.domain.comment.api.dto.request.CreateCommentRequest;
import com.example.knowledgeboard.domain.board.entiry.Board;
import com.example.knowledgeboard.domain.board.repository.BoardRepository;
import com.example.knowledgeboard.domain.comment.api.dto.request.UpdateCommentRequest;
import com.example.knowledgeboard.domain.comment.facade.CommentFacade;
import com.example.knowledgeboard.domain.user.entity.entiry.Comment;
import com.example.knowledgeboard.domain.comment.repository.CommentRepository;
import com.example.knowledgeboard.domain.user.entity.User;
import com.example.knowledgeboard.domain.board.exception.FeedNotFoundException;
import com.example.knowledgeboard.domain.user.exception.UserForbiddenException;
import com.example.knowledgeboard.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

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
                .createdAt(LocalDate.now())
                .user(user)
                .board(board)
                .build());
    }

}
