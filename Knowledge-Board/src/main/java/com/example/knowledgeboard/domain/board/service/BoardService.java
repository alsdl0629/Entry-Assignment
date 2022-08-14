package com.example.knowledgeboard.domain.board.service;

import com.example.knowledgeboard.domain.board.api.dto.request.CreateFeedRequest;
import com.example.knowledgeboard.domain.board.api.dto.request.UpdateFeedRequest;
import com.example.knowledgeboard.domain.board.api.dto.response.AllFeedsResponse;
import com.example.knowledgeboard.domain.board.api.dto.response.DetailedFeedResponse;
import com.example.knowledgeboard.domain.board.entiry.Board;
import com.example.knowledgeboard.domain.board.repository.BoardRepository;
import com.example.knowledgeboard.domain.comment.facade.CommentFacade;
import com.example.knowledgeboard.domain.user.entity.User;
import com.example.knowledgeboard.domain.board.exception.FeedNotFoundException;
import com.example.knowledgeboard.domain.user.exception.UserForbiddenException;
import com.example.knowledgeboard.domain.like.facade.LikeFacade;
import com.example.knowledgeboard.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Transactional
@Service
public class BoardService {

    private final BoardRepository boardRepository;
    private final UserFacade userFacade;
    private final LikeFacade likeFacade;
    private final CommentFacade commentFacade;

    public void createFeed(CreateFeedRequest request) {

        User user = userFacade.getUser();

        boardRepository.save(Board.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .views(0)
                .likeCounts(0)
                .user(user)
                .build());
    }

    public void updateFeed(Integer feedId, UpdateFeedRequest request) {

        Board board = boardRepository.findById(feedId)
                .orElseThrow(() -> FeedNotFoundException.EXCEPTION);

        checkUser(board);

        board.updateFeed(request.getTitle(), request.getContent());
    }

    public void deleteFeed(Integer feedId) {

        Board board = boardRepository.findById(feedId)
                .orElseThrow(() -> FeedNotFoundException.EXCEPTION);

        checkUser(board);

        boardRepository.delete(board);
    }

    private void checkUser(Board board) {
        User user = userFacade.getUser();

        if (!board.getUser().equals(user)) {
            throw UserForbiddenException.EXCEPTION;
        }
    }

    public List<AllFeedsResponse> getAllFeed() {

        return boardRepository.findAll()
                .stream().map(board -> AllFeedsResponse.builder()
                        .feedWriter(board.getUser().getAccountId())
                        .title(board.getTitle())
                        .createdDate(board.getCreatedDate())
                        .views(board.getViews())
                        .likeCounts(board.getLikeCounts())
                        .commentCounts(board.getComments().size())
                        .build())
                .collect(Collectors.toList());
    }

    public DetailedFeedResponse getOneFeed(Integer feedId) {

        Board board = boardRepository.findById(feedId)
                .orElseThrow(() -> FeedNotFoundException.EXCEPTION);

        board.addViews();

        return DetailedFeedResponse.builder()
                .boardId(board.getId())
                .userId(board.getUser().getId())
                .feedWriter(board.getUser().getAccountId())
                .title(board.getTitle())
                .content(board.getContent())
                .createdDate(board.getCreatedDate())
                .updatedDate(board.getUpdatedDate())
                .views(board.getViews())
                .liked(likeFacade.checkLiked(userFacade.getUser(), board))
                .likeCounts(board.getLikeCounts())
                .commentCounts(board.getComments().size())
                .comments(commentFacade.getComments(board))
                .build();
    }

}
