package com.example.knowledgeboard.domain.user.repository;

import com.example.knowledgeboard.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByAccountId(String accountId);

    boolean existsByAccountId(String accountId);

}
