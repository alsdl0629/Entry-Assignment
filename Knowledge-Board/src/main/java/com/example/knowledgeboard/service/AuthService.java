package com.example.knowledgeboard.service;

import com.example.knowledgeboard.dto.auth.request.LoginRequest;
import com.example.knowledgeboard.dto.auth.request.SignupRequest;
import com.example.knowledgeboard.dto.auth.response.TokenResponse;
import com.example.knowledgeboard.dto.MessageResponse;
import com.example.knowledgeboard.entity.member.Authority;
import com.example.knowledgeboard.entity.member.Member;
import com.example.knowledgeboard.entity.member.MemberRepository;
import com.example.knowledgeboard.exception.InvalidPasswordException;
import com.example.knowledgeboard.exception.UserAlreadyExistsException;
import com.example.knowledgeboard.exception.UserNotFoundException;
import com.example.knowledgeboard.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public MessageResponse signup(SignupRequest request) {

        if(memberRepository.existsByAccountId(request.getAccountId())) {
            throw new UserAlreadyExistsException();
        }

        memberRepository.save(Member.builder()
                .accountId(request.getAccountId())
                .name(request.getName())
                .age(request.getAge())
                .password(passwordEncoder.encode(request.getPassword()))
                .build());

        return MessageResponse.builder()
                .message(request.getAccountId() + "님 회원가입 성공")
                .build();
    }




}
