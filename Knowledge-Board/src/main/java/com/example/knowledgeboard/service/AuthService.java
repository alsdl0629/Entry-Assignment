package com.example.knowledgeboard.service;

import com.example.knowledgeboard.dto.auth.request.LoginRequest;
import com.example.knowledgeboard.dto.auth.response.TokenResponse;
import com.example.knowledgeboard.entity.user.User;
import com.example.knowledgeboard.entity.user.UserRepository;
import com.example.knowledgeboard.exception.InvalidPasswordException;
import com.example.knowledgeboard.exception.UserNotFoundException;
import com.example.knowledgeboard.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public TokenResponse login(LoginRequest request) {

        User user = userRepository.findByAccountId(request.getAccountId())
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);

        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw InvalidPasswordException.EXCEPTION;
        }

        String accessToken = jwtTokenProvider.generateAccessToken(user.getAccountId());
        String refreshToken = jwtTokenProvider.generateRefreshToken(user.getAccountId());

        return TokenResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

}
