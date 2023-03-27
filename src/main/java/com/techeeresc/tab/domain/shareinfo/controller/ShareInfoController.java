package com.techeeresc.tab.domain.shareinfo.controller;

import com.techeeresc.tab.domain.shareinfo.service.ShareInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/shareinfo")
public class ShareInfoController {
    private final ShareInfoService SHARE_INFO_SERVICE;

    @GetMapping
    public void crawlingEveryDay() {
        SHARE_INFO_SERVICE.crawlingScheduler();
    }
}
