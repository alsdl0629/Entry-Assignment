package com.example.knowledgeboard.service;

import com.example.knowledgeboard.dto.MessageResponse;
import com.example.knowledgeboard.dto.auth.request.SignupRequest;
import com.example.knowledgeboard.entity.board.BoardRepository;
import com.example.knowledgeboard.entity.user.Authority;
import com.example.knowledgeboard.entity.user.User;
import com.example.knowledgeboard.entity.user.UserRepository;
import com.example.knowledgeboard.exception.UserAlreadyExistsException;
import com.example.knowledgeboard.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;
    private final BoardRepository boardRepository;
    private final UserFacade userFacade;

    public MessageResponse signup(SignupRequest request) {

        if(userRepository.existsByAccountId(request.getAccountId())) {
            throw UserAlreadyExistsException.EXCEPTION;
        }

        userRepository.save(User.builder()
                .accountId(request.getAccountId())
                .name(request.getName())
                .password(passwordEncoder.encode(request.getPassword()))
                .authority(Authority.ROLE_USER)
                .build());

        return MessageResponse.builder()
                .message(request.getAccountId() + "님 회원가입 성공")
                .build();
    }

}
