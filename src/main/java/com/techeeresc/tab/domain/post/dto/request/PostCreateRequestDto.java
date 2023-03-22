package com.techeeresc.tab.domain.post.dto.request;

import com.sun.istack.NotNull;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PostCreateRequestDto {
  @NotNull
  @Schema(description = "user index id", defaultValue = "1")
  private Long memberId; // TODO: 외래키, 향후 외래키 매핑 필요, 토큰에서 받아온다.

  @NotNull
  @Schema(
      description = "post category",
      allowableValues = {"Frontend", "Backend", "DevOps", "etc"},
      defaultValue = "Backend")
  private String category;

  @NotNull
  @Schema(description = "post title", defaultValue = "스프링 부트에 스웨거 설정하기")
  private String title;

  @NotNull
  @Schema(description = "post content", defaultValue = "스웨거 3.0을 통해 프론트엔드와 협업해보아요!")
  private String content;

  @Schema(description = "post hashtags no blank", defaultValue = "#개발#프론트앤드#안녕")
  private String hashtags; // TODO: 한번에 여러개의 값을 받을 수 있도록 변경해야한다.

  @NotNull
  @Schema(
      description = "anonymous check",
      allowableValues = {"true", "false"},
      defaultValue = "false")
  private boolean isAnonymous;
}
