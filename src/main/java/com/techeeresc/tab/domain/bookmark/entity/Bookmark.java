package com.techeeresc.tab.domain.bookmark.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.techeeresc.tab.domain.member.entity.Member;
import com.techeeresc.tab.domain.post.entity.Post;
import com.techeeresc.tab.global.common.Timestamp;
import lombok.*;

import javax.persistence.*;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Getter
@Table(name = "bookmark")
public class Bookmark extends Timestamp {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(columnDefinition = "INT UNSIGNED")
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "member_id", nullable = false)
  @JsonIgnore
  private Member member;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "post_id", nullable = false)
  @JsonIgnore
  private Post post;

  @Builder
  public Bookmark(Member memberId, Post postId) {
    this.member = memberId;
    this.post = postId;
  }
}
