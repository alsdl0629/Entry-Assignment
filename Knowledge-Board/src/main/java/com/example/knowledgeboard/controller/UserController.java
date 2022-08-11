package com.example.knowledgeboard.controller;

import com.example.knowledgeboard.dto.user.request.SignupRequest;
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


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/signup")
    public void signup(@Valid @RequestBody SignupRequest request) {
        userService.signup(request);
    }

    @GetMapping("/my-page")
    public MyPageResponse getMyPage() {
        return userService.getMyPage();
    }

}
