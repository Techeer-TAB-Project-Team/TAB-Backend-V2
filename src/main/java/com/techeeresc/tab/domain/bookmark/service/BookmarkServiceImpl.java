package com.techeeresc.tab.domain.bookmark.service;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.techeeresc.tab.domain.bookmark.dto.mapper.BookmarkMapper;
import com.techeeresc.tab.domain.bookmark.dto.request.BookmarkCreateRequestDto;
import com.techeeresc.tab.domain.bookmark.entity.Bookmark;
import com.techeeresc.tab.domain.bookmark.entity.QBookmark;
import com.techeeresc.tab.domain.bookmark.repository.BookmarkQueryDslRepository;
import com.techeeresc.tab.domain.bookmark.repository.BookmarkRepository;
import com.techeeresc.tab.global.exception.exceptionclass.RequestNotFoundException;
import com.techeeresc.tab.global.status.StatusCodes;
import com.techeeresc.tab.global.status.StatusMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookmarkServiceImpl implements BookmarkService, BookmarkQueryDslRepository {

  private final BookmarkRepository REPOSITORY;
  private final BookmarkMapper MAPPER;
  private final JPAQueryFactory JPA_QUERY_FACTORY;
  private final int NULL_SIZE = 0;

  @Transactional
  public Bookmark save(BookmarkCreateRequestDto bookmarkCreateRequestDto) {
    return REPOSITORY.save(MAPPER.saveDataToEntity(bookmarkCreateRequestDto));
  }

  @Transactional
  public List<Bookmark> findAllBookmark() {
    return REPOSITORY.findAll();
  }

  @Transactional
  public List<Bookmark> deleteBookmark(Long id) {
    try {
      Bookmark bookmark = isBookmarkExisted(id);
      REPOSITORY.deleteById(bookmark.getId());
    } catch (NullPointerException exception) {
      throw new RequestNotFoundException(
          StatusMessage.NOT_FOUND.getStatusMessage(), StatusCodes.NOT_FOUND);
    }
    return findAllBookmark();
  }

  @Transactional
  public Bookmark findBookmarkById(Long id) {
    try {
      Bookmark bookmark = isBookmarkExisted(id);
      return bookmark;
    } catch (NullPointerException exception) {
      throw new RequestNotFoundException(
          StatusMessage.NOT_FOUND.getStatusMessage(), StatusCodes.NOT_FOUND);
    }
  }

  @Transactional
  public List<Bookmark> findAllBookmark(Pageable pageable) {
    QBookmark qBookmark = QBookmark.bookmark;

    try {
      List<Bookmark> bookmarks =
          JPA_QUERY_FACTORY
              .selectFrom(qBookmark)
              .offset(pageable.getOffset())
              .limit(pageable.getPageSize())
              .fetch();

      isBookmarkExistedByList(bookmarks);
      return bookmarks;
    } catch (NullPointerException exception) {
      throw new RequestNotFoundException(
          StatusMessage.NOT_FOUND.getStatusMessage(), StatusCodes.NOT_FOUND);
    }
  }

  private Bookmark isBookmarkExisted(Long id) {
    Bookmark bookmark = REPOSITORY.findById(id).orElseThrow(() -> new NullPointerException());
    return bookmark;
  }

  private void isBookmarkExistedByList(List<Bookmark> bookmarkSearchResults) {
    if (bookmarkSearchResults.size() == NULL_SIZE) {
      throw new NullPointerException();
    }
  }
}
