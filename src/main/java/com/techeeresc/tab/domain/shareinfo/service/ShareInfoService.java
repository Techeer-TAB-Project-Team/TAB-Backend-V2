package com.techeeresc.tab.domain.shareinfo.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ShareInfoService {
    @Scheduled(cron="0/10 * * * * *")
    public void crawlingScheduler() {
        System.out.println("10초에 한번씩 실행");
    }
}
