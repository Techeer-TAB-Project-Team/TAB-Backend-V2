package com.techeeresc.tab.domain.shareinfo.service;

import org.jsoup.Connection;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ShareInfoService {
    private final String WANTED = "https://www.wanted.co.kr/jobsfeed";
    @Scheduled(cron="0 0 12 1/1 * *")
    public void crawlingScheduler() {
        Connection connection = Jsoup
    }
}
