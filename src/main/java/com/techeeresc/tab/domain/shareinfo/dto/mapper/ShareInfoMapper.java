package com.techeeresc.tab.domain.shareinfo.dto.mapper;

import com.techeeresc.tab.domain.shareinfo.dto.request.ShareInfoCreateRequestDto;
import com.techeeresc.tab.domain.shareinfo.dto.response.ShareInfoResponseDto;
import com.techeeresc.tab.domain.shareinfo.entity.ShareInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ShareInfoMapper {
  public ShareInfo saveDataToEntity(ShareInfoCreateRequestDto shareInfoCreateRequestDto) {
    return ShareInfo.builder()
        .title(shareInfoCreateRequestDto.getTitle())
        .content(shareInfoCreateRequestDto.getContent())
        .link(shareInfoCreateRequestDto.getLink())
        .image(shareInfoCreateRequestDto.getImage())
        .hashtag(shareInfoCreateRequestDto.getHashtag())
        .build();
  }

  public ShareInfoResponseDto getDataFromEntity(ShareInfo shareInfo) {
    return ShareInfoResponseDto.builder()
        .id(shareInfo.getId())
        .title(shareInfo.getTitle())
        .content(shareInfo.getContent())
        .link(shareInfo.getLink())
        .image(shareInfo.getImage())
        .hashtag(shareInfo.getHashtag())
        .createdAt(shareInfo.getCreatedAt())
        .updatedAt(shareInfo.getUpdatedAt())
        .build();
  }
}
