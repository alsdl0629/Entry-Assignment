package com.example.knowledgeboard.controller;

import com.example.knowledgeboard.dto.auth.request.LoginRequest;
import com.example.knowledgeboard.dto.auth.response.TokenResponse;
import com.example.knowledgeboard.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/auth")
@RestController
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public TokenResponse login(@Valid @RequestBody LoginRequest request) {
        return authService.login(request);
    }

}
