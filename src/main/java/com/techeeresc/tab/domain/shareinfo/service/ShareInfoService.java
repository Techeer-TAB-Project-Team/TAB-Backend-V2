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
    private final String WANTED = "https://www.wanted.co.kr/wdlist/518?country=kr&job_sort=company.response_rate_order&years=-1&locations=all";

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
        // select는 받아오고자 하는 element의 클래스 이름
        Elements selects = document.select(".job-card-position");

        for (Element select : selects) {
            results.add(select.data());
        }

        return results;
    }
}
