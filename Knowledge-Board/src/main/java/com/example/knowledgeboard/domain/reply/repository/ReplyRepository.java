package com.example.knowledgeboard.domain.reply.repository;

import com.example.knowledgeboard.domain.reply.entiry.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReplyRepository extends JpaRepository<Reply, Integer> {
}
