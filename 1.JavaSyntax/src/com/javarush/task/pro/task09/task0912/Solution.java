package com.javarush.task.pro.task09.task0912;

/* 
Проверка URL-адреса
*/

public class Solution {
    public static void main(String[] args) {
        String[] urls = {"https://javarush.ru", "https://google.com", "http://wikipedia.org", "facebook.com", "https://instagram", "codegym.cc"};
        for (String url : urls) {
            String protocol = checkProtocol(url);
            String domain = checkDomain(url);

            System.out.println("У URL-адреса - " + url + ", сетевой протокол - " + protocol + ", домен - " + domain);
        }
    }

    public static String checkProtocol(String url) {
        String protocol = "неизвестный";
        if (url.startsWith("https://") || url.startsWith("http://")) {
            protocol = (url.startsWith("https://")) ? "https" : "http";
        }
        return protocol;
    }

    public static String checkDomain(String url) {
        String domain = "неизвестный";
        if (url.endsWith(".com")) {
            domain = "com";
        } else if (url.endsWith(".net")) {
            domain = "net";
        } else if (url.endsWith(".org")) {
            domain = "org";
        } else if (url.endsWith(".ru")) {
            domain = "ru";
        }
        return domain;
    }
}
