package com.example.knowledgeboard.domain.like.repository;

import com.example.knowledgeboard.domain.board.entiry.Board;
import com.example.knowledgeboard.domain.like.entiry.Like;
import com.example.knowledgeboard.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface LikeRepository extends JpaRepository<Like, Integer> {

    boolean existsByUserAndBoard(User user, Board board);

    void deleteByUserAndBoard(User user, Board board);

}
