package com.example.knowledgeboard.service;

import com.example.knowledgeboard.dto.MessageResponse;
import com.example.knowledgeboard.dto.board.request.CreateFeedRequest;
import com.example.knowledgeboard.dto.board.request.UpdateFeedRequest;
import com.example.knowledgeboard.dto.board.response.AllFeedsResponse;
import com.example.knowledgeboard.dto.board.response.DetailedFeedResponse;
import com.example.knowledgeboard.entity.board.Board;
import com.example.knowledgeboard.entity.board.BoardRepository;
import com.example.knowledgeboard.entity.user.User;
import com.example.knowledgeboard.exception.FeedNotFoundException;
import com.example.knowledgeboard.exception.ForbiddenException;
import com.example.knowledgeboard.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;
    private final UserFacade userFacade;

    public MessageResponse createFeed(CreateFeedRequest request) {

        User user = userFacade.getUser();

        boardRepository.save(Board.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .views(0)
                .user(user)
                .build());

        return MessageResponse.builder()
                .message("Board : " + request.getTitle() + "을(를) 등록했습니다.")
                .build();
    }


    public MessageResponse updateFeed(Integer feedId, UpdateFeedRequest request) {

        Board board = boardRepository.findById(feedId)
                .orElseThrow(() -> FeedNotFoundException.EXCEPTION);

        checkUser(board);

        board.updateFeed(request.getTitle(), request.getContent());
        boardRepository.save(board);

        return MessageResponse.builder()
                .message("Board : " + request.getTitle() + "을(를) 수정했습니다.")
                .build();
    }


    public MessageResponse deleteFeed(Integer feedId) {

        Board board = boardRepository.findById(feedId)
                .orElseThrow(() -> FeedNotFoundException.EXCEPTION);

        checkUser(board);

        boardRepository.delete(board);

        return new MessageResponse("Board : " + board.getTitle() + "을(를) 삭제했습니다.");
    }


    private void checkUser(Board board) {
        User user = userFacade.getUser();

        if (!board.getUser().equals(user)) {
            throw ForbiddenException.EXCEPTION;
        }
    }

    public List<AllFeedsResponse> getAllFeed() {

        return boardRepository.findAll()
                .stream().map(board -> AllFeedsResponse.builder()
                        .title(board.getTitle())
                        .createdAt(board.getUpdatedAt())
                        .updatedAt(board.getUpdatedAt())
                        .views(board.getViews())
                        .writer(board.getUser().getAccountId())
                        .build())
                .collect(Collectors.toList());
    }


    public DetailedFeedResponse getOneFeed(Integer feedId) {

        Board board = boardRepository.findById(feedId)
                .orElseThrow(() -> FeedNotFoundException.EXCEPTION);

        board.addViews();
        boardRepository.save(board);

        return DetailedFeedResponse.builder()
                .boardId(board.getId())
                .title(board.getTitle())
                .content(board.getContent())
                .createdAt(board.getUpdatedAt())
                .updatedAt(board.getUpdatedAt())
                .views(board.getViews())
                .writer(board.getUser().getAccountId())
                .build();
    }

}
