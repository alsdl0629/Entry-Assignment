package com.example.knowledgeboard.controller;

import com.example.knowledgeboard.dto.MessageResponse;
import com.example.knowledgeboard.dto.board.request.CreateFeedRequest;
import com.example.knowledgeboard.dto.board.request.UpdateFeedRequest;
import com.example.knowledgeboard.dto.board.response.AllFeedsResponse;
import com.example.knowledgeboard.dto.board.response.DetailedFeedResponse;
import com.example.knowledgeboard.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/feeds")
@RestController
public class BoardController {

    private final BoardService boardService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponse createFeed(@Valid @RequestBody CreateFeedRequest request) {
        return boardService.createFeed(request);
    }

    @PatchMapping("/{feed_id}")
    public MessageResponse updateFeed(@PathVariable(name = "feed_id") Integer id,
                                      @Valid @RequestBody UpdateFeedRequest request) {
        return boardService.updateFeed(id, request);
    }

    @DeleteMapping("/{feed_id}")
    public MessageResponse deleteFeed(@PathVariable(name = "feed_id") Integer id) {
        return boardService.deleteFeed(id);
    }

    @GetMapping("/list")
    public List<AllFeedsResponse> getAllFeed() {
        return boardService.getAllFeed();
    }

}
