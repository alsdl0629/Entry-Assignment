package com.example.knowledgeboard.domain.like.api.dto.response;

import lombok.*;

@AllArgsConstructor
@Getter
@Builder
public class LikeResponse {

    private Integer likeCounts;

    private boolean liked;

}
