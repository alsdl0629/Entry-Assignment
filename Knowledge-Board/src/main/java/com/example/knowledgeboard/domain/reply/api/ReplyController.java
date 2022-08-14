package com.example.knowledgeboard.domain.reply.api;

import com.example.knowledgeboard.domain.reply.api.dto.request.CreateReplyRequest;
import com.example.knowledgeboard.domain.reply.api.dto.request.UpdateReplyRequest;
import com.example.knowledgeboard.domain.reply.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/feeds")
@RestController
public class ReplyController {

    private final ReplyService replyService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{comment-id}/reply")
    public void addReply(@PathVariable("comment-id") Integer id,
                         @Valid @RequestBody CreateReplyRequest request) {
        replyService.addReply(id, request);
    }

}
