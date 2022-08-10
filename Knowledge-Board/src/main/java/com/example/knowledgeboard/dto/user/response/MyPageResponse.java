package com.example.knowledgeboard.dto.user.response;

import com.example.knowledgeboard.dto.board.response.AllFeedsResponse;
import lombok.*;

import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class MyPageResponse {

    private String accountId;

    private List<AllFeedsResponse> myFeeds;

}
