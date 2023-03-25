package com.techeeresc.tab.global.common;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import java.time.ZoneId;

@MappedSuperclass // JPA Entity 클래스들이 해당 클래스를 상속할 경우, 데이터 트래킹 필드로 인식
@EntityListeners(AuditingEntityListener.class) // 해당 클래스에 Auditing 기능을 표현
@Getter
public class Timestamp {
  // Entity 생성 후 저장될 때의 시간이 자동 저장
  @Column(name = "created_at")
  private LocalDateTime createdAt;

  @LastModifiedDate // 조회한 Entity의 값을 변경할 때 시간이 자동 저장
  // 수정 시 수정된 날짜로 저장되기 때문에 따로 처리해줄 필요는 없다.
  @Column(name = "updated_at")
  private LocalDateTime updatedAt;
}
