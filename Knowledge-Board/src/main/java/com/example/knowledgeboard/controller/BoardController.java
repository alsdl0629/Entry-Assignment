package com.example.knowledgeboard.controller;

import com.example.knowledgeboard.dto.MessageResponse;
import com.example.knowledgeboard.dto.board.request.CreateFeedRequest;
import com.example.knowledgeboard.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/feed")
@RestController
public class BoardController {

    private final BoardService boardService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponse createFeed(@Valid @RequestBody CreateFeedRequest request) {
        return boardService.createFeed(request);
    }

}
