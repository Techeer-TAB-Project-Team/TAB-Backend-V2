package com.techeeresc.tab.domain.shareinfo.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class ShareInfoResponseDto {
  @Schema(description = "shareInfo index id", defaultValue = "1")
  private Long id;

  @Schema(description = "title", defaultValue = "네이버 신입 공채 채용 공고")
  private String title;

  @Schema(description = "content", defaultValue = "2023년 대규모 신입을 채용합니다.")
  private String content;

  @Schema(description = "site link", defaultValue = "www.internet.com")
  private String link;

  @Schema(description = "image s3 link", defaultValue = "www.s3.image.com")
  private String image;

  @Schema(description = "hashtags", defaultValue = "#채용정보 #공채 #개발")
  private String hashtag;

  @Schema(description = "created at", defaultValue = "2023-02-21")
  private LocalDateTime createdAt;

  @Schema(description = "updated at", defaultValue = "2023-02-25")
  private LocalDateTime updatedAt;
}
