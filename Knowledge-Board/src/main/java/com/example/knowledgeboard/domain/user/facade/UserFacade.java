package com.example.knowledgeboard.domain.user.facade;

import com.example.knowledgeboard.domain.board.api.dto.response.AllFeedsResponse;
import com.example.knowledgeboard.domain.user.api.dto.response.MyPageResponse;
import com.example.knowledgeboard.domain.user.entity.User;
import com.example.knowledgeboard.domain.user.repository.UserRepository;
import com.example.knowledgeboard.domain.user.exception.AuthenticationNotFoundException;
import com.example.knowledgeboard.domain.user.exception.UserNotFoundException;
import com.example.knowledgeboard.global.security.auth.AuthDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class UserFacade {

    private final UserRepository userRepository;

    public User getUser() {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if(!(principal instanceof UserDetails)) {
            throw AuthenticationNotFoundException.EXCEPTION;
        }

        return userRepository.findByAccountId(((AuthDetails) principal).getUsername())
                .orElseThrow(()->UserNotFoundException.EXCEPTION);
    }

    public MyPageResponse getUserFeeds(User user) {

        List<AllFeedsResponse> myFeeds = user.getBoards()
                .stream().map(board -> AllFeedsResponse.builder()
                        .writer(board.getUser().getAccountId())
                        .title(board.getTitle())
                        .createdDate(board.getCreatedDate())
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
