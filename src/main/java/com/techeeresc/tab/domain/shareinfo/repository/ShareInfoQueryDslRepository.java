package com.techeeresc.tab.domain.shareinfo.repository;

import com.techeeresc.tab.domain.shareinfo.entity.ShareInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ShareInfoQueryDslRepository {
  List<ShareInfo> findAllShareInfoList(Pageable pageable);
}
