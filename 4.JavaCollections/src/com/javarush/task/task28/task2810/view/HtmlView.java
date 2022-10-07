package com.javarush.task.task28.task2810.view;

import com.javarush.task.task28.task2810.Controller;
import com.javarush.task.task28.task2810.vo.Vacancy;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HtmlView implements View {
    private Controller controller;
    private final String filePath = "./4.JavaCollections/src/" + this.getClass().getPackage().getName().replace(".", "/") + "/" + "vacancies.html";

    @Override
    public void update(List<Vacancy> vacancies) {
        try {
            updateFile(getUpdatedFileContent(vacancies));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getUpdatedFileContent(List<Vacancy> vacancies) {
        try {
            Document document = getDocument();
            Elements oldTemplate = document.getElementsByClass("template");
            Element template = oldTemplate.clone().removeAttr("style").removeClass("template").get(0);

            for (Element vacancy : document.getElementsByClass("vacancy")) {
                if (!vacancy.hasClass("template")) {
                    vacancy.remove();
                }
            }

            for (Vacancy vacancy : vacancies) {
                Element vacancyElement = template.clone();
                Element city = vacancyElement.getElementsByClass("city").get(0);
                city.appendText(vacancy.getCity());
                Element companyName = vacancyElement.getElementsByClass("companyName").get(0);
                companyName.appendText(vacancy.getCompanyName());
                Element salary = vacancyElement.getElementsByClass("salary").get(0);
                salary.appendText(vacancy.getSalary());
                Element link = vacancyElement.getElementsByAttribute("href").get(0);
                link.appendText(vacancy.getTitle());
                link.attr("href", vacancy.getUrl());
                oldTemplate.before(vacancyElement.outerHtml());
            }
            return document.html();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Some exception occurred";
    }

    private void updateFile(String s) throws IOException {
        try (FileWriter fileWriter = new FileWriter(filePath)) {
            fileWriter.write(s);
        }
    }

    protected Document getDocument() throws IOException {
        File file = new File(filePath);
        return Jsoup.parse(file, "UTF-8");
    }

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void userCitySelectEmulationMethod() {
        controller.onCitySelect("Penza");
    }
}
