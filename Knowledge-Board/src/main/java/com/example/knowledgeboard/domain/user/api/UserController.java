package com.example.knowledgeboard.domain.user.api;

import com.example.knowledgeboard.domain.user.api.dto.request.SignupRequest;
import com.example.knowledgeboard.domain.user.api.dto.response.MyPageResponse;
import com.example.knowledgeboard.domain.user.service.UserService;
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

    @GetMapping("/{user_id}")
    public MyPageResponse getUserPage(@PathVariable("user_id") Integer id) {
        return userService.getUserPage(id);
    }

}
