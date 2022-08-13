package com.example.knowledgeboard.domain.like.api;

import com.example.knowledgeboard.domain.like.api.dto.response.LikeResponse;
import com.example.knowledgeboard.domain.like.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class LikeController {

    private final LikeService likeService;

    @PutMapping("/feeds/{feed-id}/like")
    public LikeResponse liked(@PathVariable("feed-id") Integer id) {
        return likeService.liked(id);
    }

}
