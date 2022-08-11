package com.example.knowledgeboard.controller;

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

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void createFeed(@Valid @RequestBody CreateFeedRequest request) {
        boardService.createFeed(request);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/{feed_id}")
    public void updateFeed(@PathVariable(name = "feed_id") Integer id,
                                      @Valid @RequestBody UpdateFeedRequest request) {
        boardService.updateFeed(id, request);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{feed_id}")
    public void deleteFeed(@PathVariable(name = "feed_id") Integer id) {
        boardService.deleteFeed(id);
    }

    @GetMapping
    public List<AllFeedsResponse> getAllFeed() {
        return boardService.getAllFeed();
    }

    @GetMapping("/{feed_id}")
    public DetailedFeedResponse getOneFeed(@PathVariable(name = "feed_id") Integer id) {
        return boardService.getOneFeed(id);
    }

}
