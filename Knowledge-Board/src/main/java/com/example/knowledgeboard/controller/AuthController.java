package com.example.knowledgeboard.controller;

import com.example.knowledgeboard.dto.auth.request.SignupRequest;
import com.example.knowledgeboard.dto.MessageResponse;
import com.example.knowledgeboard.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/auth")
@RestController
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponse signup(@Valid @RequestBody SignupRequest request) {
        return authService.signup(request);
    }

}
