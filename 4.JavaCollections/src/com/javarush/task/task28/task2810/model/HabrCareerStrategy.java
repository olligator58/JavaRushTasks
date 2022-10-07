package com.javarush.task.task28.task2810.model;

import com.javarush.task.task28.task2810.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HabrCareerStrategy implements Strategy {
//    private static final String URL_FORMAT = "https://javarush.ru/testdata/big28data2.html?q=java+%s&page=%d";
    private static final String URL_FORMAT = "https://career.habr.com/vacancies?q=java+%s&page=%d";

    @Override
    public List<Vacancy> getVacancies(String searchString) {
        List<Vacancy> result = new ArrayList<>();
        int page = 0;

        try {
            while (true) {
                Document document = getDocument(searchString, page);
                Elements elements = document.getElementsByClass("job");

                if (elements.isEmpty()) {
                    break;
                }
                for (Element element : elements) {
                    Elements title = element.getElementsByClass("title");
                    Elements href = title.get(0).getElementsByAttribute("href");

                    Elements salary = element.getElementsByClass("count");
                    Elements city = element.getElementsByClass("location");
                    Elements company = element.getElementsByClass("company_name");

                    Vacancy vacancy = new Vacancy();
                    vacancy.setTitle(title.get(0).attr("title"));
                    vacancy.setSalary(salary.size() > 0 ? salary.get(0).text() : "");
                    vacancy.setCity(city.size() > 0 ? city.get(0).text() : "");
                    vacancy.setCompanyName(company.get(0).text());
                    vacancy.setSiteName("https://career.habr.com");
                    vacancy.setUrl("https://career.habr.com" + href.get(0).attr("href"));
                    result.add(vacancy);
                }
                page++;
            }

        } catch (IOException ignore) {
        }

        return result;
    }

    protected Document getDocument(String searchString, int page) throws IOException {
        return Jsoup.connect(String.format(URL_FORMAT, searchString, page))
                .userAgent("Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/105.0.0.0 Safari/537.36")
                .referrer("https://hh.ru/search/vacancy?text=java+Penza")
                .get();
    }
}
