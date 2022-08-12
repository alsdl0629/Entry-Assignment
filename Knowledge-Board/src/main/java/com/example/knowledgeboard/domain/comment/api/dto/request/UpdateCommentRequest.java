package com.example.knowledgeboard.domain.comment.api.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
public class UpdateCommentRequest {

    @NotBlank(message = "content는 필수 항목입니다.")
    @Size(max = 250, message = "content는 250글자 이하여야 합니다.")
    private String content;

}
