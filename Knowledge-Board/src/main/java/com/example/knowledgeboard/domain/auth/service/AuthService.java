package com.example.knowledgeboard.domain.auth.service;

import com.example.knowledgeboard.domain.auth.api.dto.request.LoginRequest;
import com.example.knowledgeboard.domain.auth.api.dto.response.TokenResponse;
import com.example.knowledgeboard.domain.auth.exception.RefreshTokenNotFoundException;
import com.example.knowledgeboard.domain.refreshtoken.entity.RefreshToken;
import com.example.knowledgeboard.domain.refreshtoken.repository.RefreshTokenRepository;
import com.example.knowledgeboard.domain.user.entity.User;
import com.example.knowledgeboard.domain.user.repository.UserRepository;
import com.example.knowledgeboard.domain.auth.exception.InvalidPasswordException;
import com.example.knowledgeboard.domain.user.exception.UserNotFoundException;
import com.example.knowledgeboard.global.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class AuthService {

    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;
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

    public TokenResponse reissue(String refresh) {

        RefreshToken refreshToken = refreshTokenRepository.findByRefreshToken(refresh)
                .orElseThrow(() -> RefreshTokenNotFoundException.EXCEPTION);

        String newAccessToken = jwtTokenProvider.generateAccessToken(refreshToken.getId());

        return TokenResponse.builder()
                .accessToken(newAccessToken)
                .refreshToken(refresh)
                .build();
    }

}
