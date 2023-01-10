package com.techeeresc.tab.domain.bookmark.controller;

import com.techeeresc.tab.domain.bookmark.dto.mapper.BookmarkMapper;
import com.techeeresc.tab.domain.bookmark.dto.request.BookmarkCreateRequestDto;
import com.techeeresc.tab.domain.bookmark.dto.request.PagingDTO;
import com.techeeresc.tab.domain.bookmark.dto.response.BookmarkResponseDto;
import com.techeeresc.tab.domain.bookmark.entity.Bookmark;
import com.techeeresc.tab.domain.bookmark.service.BookmarkService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/bookmark")
public class BookmarkController {
    private final BookmarkService BOOKMARK_SERVICE;
    private final BookmarkMapper BOOKMARK_MAPPER;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookmarkResponseDto createBookmark(@RequestBody BookmarkCreateRequestDto bookmarkCreateRequestDto) {
        Bookmark insertBookmarkResult = BOOKMARK_SERVICE.save(bookmarkCreateRequestDto);
        return BOOKMARK_MAPPER.getDataFromEntity(insertBookmarkResult);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Bookmark> readAllBookmark() {
        return BOOKMARK_SERVICE.findAllBookmark();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookmarkResponseDto> findBookmark(@PathVariable Long id) {
        Bookmark findBookmarkResult = BOOKMARK_SERVICE.findBookmarkById(id);
        return new ResponseEntity<>(BOOKMARK_MAPPER.getDataFromEntity(findBookmarkResult), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Bookmark> deleteBookmark(@PathVariable Long id) {
        List<Bookmark> bookmarks = BOOKMARK_SERVICE.deleteBookmark(id);
        return bookmarks;
    }

    @GetMapping
    public ResponseEntity<PageImpl<Bookmark>> findAllBookmarks(PagingDTO pagingDTO) {
        Pageable pageable = pagingDTO.of();
        PageImpl<Bookmark> bookmarks = BOOKMARK_SERVICE.findAll(pageable);
        return new ResponseEntity<>(bookmarks, HttpStatus.OK);
    }
}

