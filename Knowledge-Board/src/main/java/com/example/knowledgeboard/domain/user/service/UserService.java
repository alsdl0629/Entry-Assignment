package com.example.knowledgeboard.domain.user.service;

import com.example.knowledgeboard.domain.user.api.dto.request.SignupRequest;
import com.example.knowledgeboard.domain.user.api.dto.response.MyPageResponse;
import com.example.knowledgeboard.domain.user.entity.Authority;
import com.example.knowledgeboard.domain.user.entity.User;
import com.example.knowledgeboard.domain.user.exception.UserNotFoundException;
import com.example.knowledgeboard.domain.user.repository.UserRepository;
import com.example.knowledgeboard.domain.user.exception.UserAlreadyExistsException;
import com.example.knowledgeboard.domain.like.facade.LikeFacade;
import com.example.knowledgeboard.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final LikeFacade likeFacade;
    private final PasswordEncoder passwordEncoder;
    private final UserFacade userFacade;

    public void signup(SignupRequest request) {

        if(userRepository.existsByAccountId(request.getAccountId())) {
            throw UserAlreadyExistsException.EXCEPTION;
        }

        userRepository.save(User.builder()
                .name(request.getName())
                .accountId(request.getAccountId())
                .introduction(request.getIntroduction())
                .password(passwordEncoder.encode(request.getPassword()))
                .authority(Authority.ROLE_USER)
                .build());
    }

    public MyPageResponse getMyPage() {

        User user = userFacade.getUser();

        return userFacade.getUserFeeds(user);
    }


    public MyPageResponse getUserPage(Integer id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);

        return userFacade.getUserFeeds(user);
    }
}
