package com.example.knowledgeboard.entity.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByAccountId(String accountId);

    boolean existsByAccountId(String accountId);

}
