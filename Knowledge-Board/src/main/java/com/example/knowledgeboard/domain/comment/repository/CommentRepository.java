package com.example.knowledgeboard.domain.comment.repository;

import com.example.knowledgeboard.domain.comment.entiry.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
}
