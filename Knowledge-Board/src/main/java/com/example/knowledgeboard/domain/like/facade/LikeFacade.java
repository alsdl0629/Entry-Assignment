package com.example.knowledgeboard.domain.like.facade;

import com.example.knowledgeboard.domain.board.entiry.Board;
import com.example.knowledgeboard.domain.like.repository.LikeRepository;
import com.example.knowledgeboard.domain.user.entity.User;
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
