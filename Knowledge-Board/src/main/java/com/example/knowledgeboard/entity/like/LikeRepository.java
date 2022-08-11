package com.example.knowledgeboard.entity.like;

import com.example.knowledgeboard.entity.board.Board;
import com.example.knowledgeboard.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface LikeRepository extends JpaRepository<Like, Integer> {

    boolean existsByUserAndBoard(User user, Board board);

    void deleteByUserAndBoard(User user, Board board);

}
