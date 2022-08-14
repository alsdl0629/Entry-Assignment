package com.example.knowledgeboard.domain.board.api.dto.response;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Builder
public class AllFeedsResponse {

    private String feedWriter;

    private String title;

    private LocalDate createdDate;

    private Integer views;

    private Integer likeCounts;

    private Integer commentCounts;

}
