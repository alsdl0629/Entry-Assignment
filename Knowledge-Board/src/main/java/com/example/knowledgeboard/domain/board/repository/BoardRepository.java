package com.example.knowledgeboard.domain.board.repository;

import com.example.knowledgeboard.domain.board.entiry.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Integer> {
}
