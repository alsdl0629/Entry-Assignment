package com.example.knowledgeboard.domain.like.service;

import com.example.knowledgeboard.domain.like.api.dto.response.LikeResponse;
import com.example.knowledgeboard.domain.board.entiry.Board;
import com.example.knowledgeboard.domain.board.repository.BoardRepository;
import com.example.knowledgeboard.domain.like.entiry.Like;
import com.example.knowledgeboard.domain.like.repository.LikeRepository;
import com.example.knowledgeboard.domain.user.entity.User;
import com.example.knowledgeboard.domain.board.exception.FeedNotFoundException;
import com.example.knowledgeboard.domain.like.facade.LikeFacade;
import com.example.knowledgeboard.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class LikeService {

    private final LikeRepository likeRepository;
    private final BoardRepository boardRepository;
    private final UserFacade userFacade;
    private final LikeFacade likeFacade;

    public LikeResponse liked(Integer id) {

        User user = userFacade.getUser();

        Board board = boardRepository.findById(id)
                .orElseThrow(()-> FeedNotFoundException.EXCEPTION);

        if(likeFacade.checkLiked(user, board)) {
            board.minusLikeCount();
            return removeLike(user, board);
        }else {
            board.addLikeCount();
            return addLike(user, board);
        }
    }

    private LikeResponse addLike(User user, Board board) {

        likeRepository.save(Like.builder()
                .user(user)
                .board(board)
                .build());

        return LikeResponse.builder()
                .likeCounts(board.getLikeCounts())
                .liked(likeFacade.checkLiked(user, board))
                .build();
    }

    private LikeResponse removeLike(User user, Board board) {

        likeRepository.deleteByUserAndBoard(user, board);

        return LikeResponse.builder()
                .likeCounts(board.getLikeCounts())
                .liked(likeFacade.checkLiked(user, board))
                .build();
    }

}
