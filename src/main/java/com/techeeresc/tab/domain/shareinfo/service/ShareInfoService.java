package com.techeeresc.tab.domain.shareinfo.service;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Service
public class ShareInfoService {
    private final String WANTED = "https://www.wanted.co.kr/jobsfeed";

    @Scheduled(cron="0 0 12 1/1 * *")
    public void wantedCrawlingScheduler() {
        Connection connection = Jsoup.connect(WANTED);
        Document document = null;

        try {
            document = connection.get();
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        List<String> results = getData(document);

        System.out.println(results);
    }

    private List<String> getData(Document document) {
        List<String> results = new ArrayList<>();
        Elements selects = document.select(".sentence-list");

        for (Element select : selects) {
            System.out.println(selects.html());
        }

        return results;
    }
}
