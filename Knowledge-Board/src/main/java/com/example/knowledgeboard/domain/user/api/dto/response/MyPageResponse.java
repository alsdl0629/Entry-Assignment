package com.example.knowledgeboard.domain.user.api.dto.response;

import com.example.knowledgeboard.domain.board.api.dto.response.AllFeedsResponse;
import lombok.*;

import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class MyPageResponse {

    private String accountId;

    private String introduction;

    private List<AllFeedsResponse> myFeeds;

}
