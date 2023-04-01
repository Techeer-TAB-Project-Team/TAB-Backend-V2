package com.techeeresc.tab.domain.post.dto.response;

import com.techeeresc.tab.domain.post.entity.Post;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class PostDataAndLengthDto {
    @Schema(description = "post Json Dto", defaultValue = "{ id: 1, memberId: 2, ... }")
    private List<Post> posts;

    @Schema(description = "post Length - 프론트에서 페이지 카운트 할 때 /10해서 사용할 것", defaultValue = "125")
    private Integer postLength;
}
