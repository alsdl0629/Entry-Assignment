package com.example.knowledgeboard.domain.board.api;

import com.example.knowledgeboard.domain.board.api.dto.request.CreateFeedRequest;
import com.example.knowledgeboard.domain.board.api.dto.request.UpdateFeedRequest;
import com.example.knowledgeboard.domain.board.api.dto.response.AllFeedsResponse;
import com.example.knowledgeboard.domain.board.api.dto.response.DetailedFeedResponse;
import com.example.knowledgeboard.domain.board.service.BoardService;
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
    @PatchMapping("/{feed-id}")
    public void updateFeed(@PathVariable(name = "feed-id") Integer id,
                                      @Valid @RequestBody UpdateFeedRequest request) {
        boardService.updateFeed(id, request);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{feed-id}")
    public void deleteFeed(@PathVariable(name = "feed-id") Integer id) {
        boardService.deleteFeed(id);
    }

    @GetMapping
    public List<AllFeedsResponse> getAllFeed() {
        return boardService.getAllFeed();
    }

    @GetMapping("/{feed-id}")
    public DetailedFeedResponse getOneFeed(@PathVariable(name = "feed-id") Integer id) {
        return boardService.getOneFeed(id);
    }

}
