package com.example.knowledgeboard.facade;

import com.example.knowledgeboard.entity.board.Board;
import com.example.knowledgeboard.entity.like.LikeRepository;
import com.example.knowledgeboard.entity.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class LikeFacade {

    private final LikeRepository likeRepository;

    public boolean checkLiked(User user, Board board) {
        return likeRepository.existsByUserAndBoard(user, board);
    }
}
