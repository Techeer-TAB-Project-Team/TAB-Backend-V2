package com.techeeresc.tab.domain.bookmark.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class BookmarkResponseDto {
  @Schema(description = "bookmark index id", defaultValue = "1")
  private Long id;

  @Schema(description = "post index id", defaultValue = "1")
  private Long postId;

  @Schema(description = "user index id", defaultValue = "1")
  private Long memberId;

  @Schema(description = "created at", defaultValue = "2023-10-22")
  private LocalDateTime createdAt;

  @Schema(description = "updated at", defaultValue = "2023-12-01")
  private LocalDateTime updatedAt;
}
