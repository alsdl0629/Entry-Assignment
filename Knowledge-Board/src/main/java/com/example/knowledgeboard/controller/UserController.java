package com.example.knowledgeboard.controller;

import com.example.knowledgeboard.dto.MessageResponse;
import com.example.knowledgeboard.dto.auth.request.SignupRequest;
import com.example.knowledgeboard.dto.user.response.MyPageResponse;
import com.example.knowledgeboard.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/users")
@RestController
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponse signup(@Valid @RequestBody SignupRequest request) {
        return userService.signup(request);
    }

}
