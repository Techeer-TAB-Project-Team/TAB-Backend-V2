package com.techeeresc.tab.domain.post.repository;

import com.techeeresc.tab.domain.post.dto.response.PostDataAndLengthDto;
import com.techeeresc.tab.domain.post.entity.Post;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostQueryDslRepository {
  PostDataAndLengthDto findByTitleContainsWordWithQueryDsl(String word, Pageable pageable);

  PostDataAndLengthDto findAllPostListWithQueryDsl(Pageable pageable);
}
