package com.example.knowledgeboard.controller;

import com.example.knowledgeboard.dto.like.response.LikeResponse;
import com.example.knowledgeboard.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class LikeController {

    private final LikeService likeService;

    @PutMapping("/feeds/{feed_id}/like")
    public LikeResponse liked(@PathVariable("feed_id") Integer id) {
        return likeService.liked(id);
    }

}
