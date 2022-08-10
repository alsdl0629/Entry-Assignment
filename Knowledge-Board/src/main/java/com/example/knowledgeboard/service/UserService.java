package com.example.knowledgeboard.service;

import com.example.knowledgeboard.dto.MessageResponse;
import com.example.knowledgeboard.dto.user.request.SignupRequest;
import com.example.knowledgeboard.dto.board.response.AllFeedsResponse;
import com.example.knowledgeboard.dto.user.response.MyPageResponse;
import com.example.knowledgeboard.entity.board.BoardRepository;
import com.example.knowledgeboard.entity.user.Authority;
import com.example.knowledgeboard.entity.user.User;
import com.example.knowledgeboard.entity.user.UserRepository;
import com.example.knowledgeboard.exception.UserAlreadyExistsException;
import com.example.knowledgeboard.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


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

    public MyPageResponse getMyPage() {

        User user = userFacade.getUser();

        List<AllFeedsResponse> myFeeds = user.getBoards()
                .stream().map(board -> AllFeedsResponse.builder()
                        .title(board.getTitle())
                        .createdAt(board.getUpdatedAt())
                        .updatedAt(board.getUpdatedAt())
                        .views(board.getViews())
                        .writer(board.getUser().getAccountId())
                        .build())
                .collect(Collectors.toList());

        return MyPageResponse.builder()
                .accountId(user.getAccountId())
                .myFeeds(myFeeds)
                .build();
    }

}
