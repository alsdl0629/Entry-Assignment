package com.example.knowledgeboard.domain.user.service;

import com.example.knowledgeboard.domain.user.api.dto.request.SignupRequest;
import com.example.knowledgeboard.domain.board.api.dto.response.AllFeedsResponse;
import com.example.knowledgeboard.domain.user.api.dto.response.MyPageResponse;
import com.example.knowledgeboard.domain.user.entity.Authority;
import com.example.knowledgeboard.domain.user.entity.User;
import com.example.knowledgeboard.domain.user.repository.UserRepository;
import com.example.knowledgeboard.domain.user.exception.UserAlreadyExistsException;
import com.example.knowledgeboard.domain.like.facade.LikeFacade;
import com.example.knowledgeboard.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


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

        List<AllFeedsResponse> myFeeds = user.getBoards()
                .stream().map(board -> AllFeedsResponse.builder()
                        .writer(board.getUser().getAccountId())
                        .title(board.getTitle())
                        .createdAt(board.getCreatedAt())
                        .views(board.getViews())
                        .likeCounts(board.getLikeCounts())
                        .build())
                .collect(Collectors.toList());

        return MyPageResponse.builder()
                .accountId(user.getAccountId())
                .introduction(user.getIntroduction())
                .myFeeds(myFeeds)
                .build();
    }

}
