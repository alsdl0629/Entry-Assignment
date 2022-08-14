package com.example.knowledgeboard.domain.auth.api;

import com.example.knowledgeboard.domain.auth.api.dto.request.LoginRequest;
import com.example.knowledgeboard.domain.auth.api.dto.response.TokenResponse;
import com.example.knowledgeboard.domain.auth.service.AuthService;
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

    @PutMapping("/reissue")
    public TokenResponse reissue(@RequestHeader("Refresh-Token") String refresh) {
        return authService.reissue(refresh);
    }

}
