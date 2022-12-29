package com.techeeresc.tab.domain.post.controller;

import com.techeeresc.tab.domain.post.dto.mapper.PostMapper;
import com.techeeresc.tab.domain.post.dto.request.PostCreateRequestDto;
import com.techeeresc.tab.domain.post.dto.request.PostUpdateRequestDto;
import com.techeeresc.tab.domain.post.dto.response.PostResponseDto;
import com.techeeresc.tab.domain.post.entity.Post;
import com.techeeresc.tab.domain.post.service.PostService;
import com.techeeresc.tab.global.exception.customexception.BadRequestBodyException;
import com.techeeresc.tab.global.exception.customexception.RequestNotFoundException;
import com.techeeresc.tab.global.exception.handler.GlobalExceptionHandler;
import com.techeeresc.tab.global.exception.response.ErrorResponse;
import com.techeeresc.tab.global.status.StatusCodes;
import com.techeeresc.tab.global.status.StatusMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/post")
public class PostController {
    private final PostService POST_SERVICE;
    private final PostMapper POST_MAPPER;
    private final GlobalExceptionHandler GLOBAL_EXCEPTION_HANDLER;

    @PostMapping
    public ResponseEntity<PostResponseDto> createPost(@RequestBody PostCreateRequestDto postCreateRequestDto) {
        Post insertPostResult = POST_SERVICE.insertPost(postCreateRequestDto);
        return new ResponseEntity(POST_MAPPER.getDataFromEntity(insertPostResult), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Post>> findAllPosts() {
        return new ResponseEntity<>(POST_SERVICE.findAllPost(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<PostResponseDto> updatePost(@RequestBody PostUpdateRequestDto postUpdateRequestDto) {
        Post updatePostResult = POST_SERVICE.updatePost(postUpdateRequestDto);
        return new ResponseEntity<>(POST_MAPPER.getDataFromEntity(updatePostResult), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<List<Post>> deletePost(@PathVariable Long id) {
        List<Post> posts = POST_SERVICE.deletePost(id);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostResponseDto> findPostAndIncreaseViews(@PathVariable Long id) {
        Post findPostResult = POST_SERVICE.findPostByIdAndIncreaseViews(id);
        return new ResponseEntity<>(POST_MAPPER.getDataFromEntity(findPostResult), HttpStatus.OK);
    }

    @PostMapping("/{id}")
    public ResponseEntity<PostResponseDto> increaseLikeNumbers(@PathVariable Long id) {
        Post clickLikePost = POST_SERVICE.increaseLikeNumbers(id);
        return new ResponseEntity<>(POST_MAPPER.getDataFromEntity(clickLikePost), HttpStatus.CREATED);
    }

    @GetMapping("/search/{word:.+}")   /* PathVariable에 특수문자 허용 */
    public ResponseEntity<List<Post>> findPostSearchResults(@PathVariable String word) {
        List<Post> postSearchResults = POST_SERVICE.findByTitleContainsWordWithQueryDsl(word);
        return new ResponseEntity<>(postSearchResults, HttpStatus.OK);
    }
}
