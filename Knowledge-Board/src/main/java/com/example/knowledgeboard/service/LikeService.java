package com.example.knowledgeboard.service;

import com.example.knowledgeboard.dto.like.response.LikeResponse;
import com.example.knowledgeboard.entity.board.Board;
import com.example.knowledgeboard.entity.board.BoardRepository;
import com.example.knowledgeboard.entity.like.Like;
import com.example.knowledgeboard.entity.like.LikeRepository;
import com.example.knowledgeboard.entity.user.User;
import com.example.knowledgeboard.exception.FeedNotFoundException;
import com.example.knowledgeboard.facade.LikeFacade;
import com.example.knowledgeboard.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class LikeService {

    private final LikeRepository likeRepository;
    private final BoardRepository boardRepository;
    private final UserFacade userFacade;
    private final LikeFacade likeFacade;

    @Transactional
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

    @Transactional
    public LikeResponse addLike(User user, Board board) {

        likeRepository.save(Like.builder()
                .user(user)
                .board(board)
                .build());

        return LikeResponse.builder()
                .likeCounts(board.getLikeCounts())
                .liked(likeFacade.checkLiked(user, board))
                .build();
    }

    @Transactional
    public LikeResponse removeLike(User user, Board board) {

        likeRepository.deleteByUserAndBoard(user, board);

        return LikeResponse.builder()
                .likeCounts(board.getLikeCounts())
                .liked(likeFacade.checkLiked(user, board))
                .build();
    }

}
