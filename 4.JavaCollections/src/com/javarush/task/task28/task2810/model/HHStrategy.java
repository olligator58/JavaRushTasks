package com.javarush.task.task28.task2810.model;

import com.javarush.task.task28.task2810.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class HHStrategy implements Strategy {
//    private static final String URL_FORMAT = "https://hh.ru/search/vacancy?text=java+%s&page=%d";
    private static final String URL_FORMAT = "https://javarush.ru/testdata/big28data.html?text=java+%s&page=%d";

    @Override
    public List<Vacancy> getVacancies(String searchString) {
        int page = 0;

        try {
            Document document = getDocument(searchString, page);
            Elements elements = document.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy");
            System.out.println(elements);

        } catch (IOException ignore) {
        }

        return Collections.EMPTY_LIST;
    }

    protected Document getDocument(String searchString, int page) throws IOException {
        return Jsoup.connect(String.format(URL_FORMAT, searchString, page))
                .userAgent("Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/105.0.0.0 Safari/537.36")
                .referrer("https://hh.ru/search/vacancy?text=java+Penza")
                .get();
    }

    //debug
    public static void main(String[] args) {
        new HHStrategy().getVacancies("Penza");
    }
}
