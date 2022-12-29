package com.techeeresc.tab.domain.post.service;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.techeeresc.tab.domain.post.dto.mapper.PostMapper;
import com.techeeresc.tab.domain.post.dto.request.PostCreateRequestDto;
import com.techeeresc.tab.domain.post.dto.request.PostUpdateRequestDto;
import com.techeeresc.tab.domain.post.entity.Post;
import com.techeeresc.tab.domain.post.entity.QPost;
import com.techeeresc.tab.domain.post.repository.PostQueryDslRepository;
import com.techeeresc.tab.domain.post.repository.PostRepository;
import com.techeeresc.tab.global.exception.customexception.RequestNotFoundException;
import com.techeeresc.tab.global.status.StatusCodes;
import com.techeeresc.tab.global.status.StatusMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PostService implements PostQueryDslRepository {
    private final PostRepository POST_REPOSITORY;
    private final PostMapper POST_MAPPER;
    private final JPAQueryFactory JPA_QUERY_FACTORY;

    @Transactional
    public Post insertPost(PostCreateRequestDto postCreateRequestDto) {
        return POST_REPOSITORY.save(POST_MAPPER.saveDataToEntity(postCreateRequestDto));
    }

    @Transactional
    public List<Post> findAllPost() {
        return POST_REPOSITORY.findAll();
    }

    @Transactional
    public Post updatePost(PostUpdateRequestDto postUpdateRequestDto) {
        try {
            Post post = isPostExisted(postUpdateRequestDto.getId());
            return post.updatePost(postUpdateRequestDto);
        } catch(NullPointerException exception) {
            throw new RequestNotFoundException(StatusMessage.NOT_FOUND.getStatusMessage(), StatusCodes.NOT_FOUND);
        }
    }

    @Transactional
    public Post increaseLikeNumbers(Long id) {
        try {
            Post post = isPostExisted(id);
            return post.increaseLikeNumbers(post.getLikeNumbers());
        } catch(NullPointerException exception) {
            throw new RequestNotFoundException(StatusMessage.NOT_FOUND.getStatusMessage(), StatusCodes.NOT_FOUND);
        }
    }

    @Transactional
    public List<Post> deletePost(Long id) {
        try {
            Post post = isPostExisted(id);
            POST_REPOSITORY.deleteById(post.getId());
        } catch (NullPointerException exception) {
            throw new RequestNotFoundException(StatusMessage.NOT_FOUND.getStatusMessage(), StatusCodes.NOT_FOUND);
        }

        return findAllPost();
    }

    @Transactional
    public Post findPostByIdAndIncreaseViews(Long id) {
        try {
            Post post = isPostExisted(id);
            post = increaseViews(post);
            return post;
        } catch(NullPointerException exception) {
            throw new RequestNotFoundException(StatusMessage.NOT_FOUND.getStatusMessage(), StatusCodes.NOT_FOUND);
        }
    }

    @Transactional
    public Post findPostById(Long id) {
        try {
            Post post = isPostExisted(id);
            return post;
        } catch(NullPointerException exception) {
            throw new RequestNotFoundException(StatusMessage.NOT_FOUND.getStatusMessage(), StatusCodes.NOT_FOUND);
        }
    }

    @Transactional
    public List<Post> findByTitleContainsWordWithQueryDsl(String word) {
        QPost qPost = QPost.post;

        try {
            List<Post> postSearchResults = JPA_QUERY_FACTORY.selectFrom(qPost)
                    .where(qPost.title.contains(word)).fetch();
            return postSearchResults;
        } catch (NullPointerException exception) {
            throw new RequestNotFoundException(StatusMessage.NOT_FOUND.getStatusMessage(), StatusCodes.NOT_FOUND);
        }
    }

    private Post increaseViews(Post post) {
        return post.increaseViews(post.getViews());
    }

    private Post isPostExisted(Long id) {
        Post post = POST_REPOSITORY.findById(id).orElseThrow(() ->
                new NullPointerException());

        return post;
    }
}