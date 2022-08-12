package com.example.knowledgeboard.dto.board.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@AllArgsConstructor
public class UpdateFeedRequest {

    @NotBlank(message = "title은 필수 항목입니다.")
    @Size(max = 30, message = "title은 30글자 이하여야 합니다.")
    private String title;

    @NotBlank(message = "content는 필수 항목입니다.")
    private String content;

}
