package com.techeeresc.tab.domain.shareinfo.controller;

import com.techeeresc.tab.domain.shareinfo.service.ShareInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/shareinfo")
public class ShareInfoController {
    private final ShareInfoService SHARE_INFO_SERVICE;

    @GetMapping
    @Async
    @Scheduled(cron="0 0 12 1/1 * *")   // 하루 한 번 수행
    public void wantedCrawlingEveryDay() {
        SHARE_INFO_SERVICE.wantedCrawlingScheduler();
    }
}
