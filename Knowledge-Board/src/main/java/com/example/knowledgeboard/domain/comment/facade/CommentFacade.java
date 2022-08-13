package com.example.knowledgeboard.domain.comment.facade;

import com.example.knowledgeboard.domain.board.entiry.Board;
import com.example.knowledgeboard.domain.comment.api.dto.response.CommentResponse;
import com.example.knowledgeboard.domain.comment.exception.CommentNotFoundException;
import com.example.knowledgeboard.domain.comment.repository.CommentRepository;
import com.example.knowledgeboard.domain.user.entity.entiry.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class CommentFacade {

    private final CommentRepository commentRepository;

    public List<CommentResponse> getComments(Board board) {

        return board.getComments()
                .stream().map(comment -> CommentResponse.builder()
                        .commentId(comment.getId())
                        .userId(comment.getUser().getId())
                        .writer(comment.getUser().getAccountId())
                        .content(comment.getContent())
                        .createdAt(comment.getCreatedAt())
                        .build())
                .collect(Collectors.toList());
    }

    public Comment getByCommentId(Integer commentId) {
        return commentRepository.findById(commentId)
                .orElseThrow(() -> CommentNotFoundException.EXCEPTION);
    }

}
