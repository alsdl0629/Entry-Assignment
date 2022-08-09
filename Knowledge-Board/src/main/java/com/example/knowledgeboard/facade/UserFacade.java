package com.example.knowledgeboard.facade;

import com.example.knowledgeboard.entity.user.User;
import com.example.knowledgeboard.entity.user.UserRepository;
import com.example.knowledgeboard.exception.AuthenticationNotFoundException;
import com.example.knowledgeboard.exception.UserNotFoundException;
import com.example.knowledgeboard.security.auth.AuthDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

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

}
