package com.example.knowledgeboard.domain.comment.api;

import com.example.knowledgeboard.domain.comment.api.dto.request.CreateCommentRequest;
import com.example.knowledgeboard.domain.comment.api.dto.request.UpdateCommentRequest;
import com.example.knowledgeboard.domain.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/feeds")
@RestController
public class CommentController {

    private final CommentService commentService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{feed-id}/comment")
    public void addComment(@PathVariable("feed-id") Integer id,
                           @Valid @RequestBody CreateCommentRequest request) {
        commentService.addComment(id, request);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/{comment-id}/comment")
    public void updateComment(@PathVariable("comment-id") Integer id,
                              @Valid @RequestBody UpdateCommentRequest request) {
        commentService.updateComment(id, request);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{comment-id}/comment")
    public void removeComment(@PathVariable("comment-id") Integer id) {
        commentService.removeComment(id);
    }

}
