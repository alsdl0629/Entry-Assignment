package com.example.knowledgeboard.dto.like.response;

import lombok.*;

@AllArgsConstructor
@Getter
@Builder
public class LikeResponse {

    private Integer likeCounts;

    private boolean liked;

}
