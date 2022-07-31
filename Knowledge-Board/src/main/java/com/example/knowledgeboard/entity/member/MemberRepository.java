package com.example.knowledgeboard.entity.member;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Integer> {

    Optional<Member> findByAccountId(String accountId);

    boolean existsByAccountId(String accountId);

}
