package com.javarush.task.task22.task2202;

/* 
Найти подстроку
*/

public class Solution {
    public static void main(String[] args) {
        System.out.println(getPartOfString("JavaRush - лучший сервис обучения Java."));
        System.out.println(getPartOfString("Амиго и Диего лучшие друзья!"));
        System.out.println(getPartOfString("Амиго и Диего"));
        System.out.println(getPartOfString(null));
    }

    public static String getPartOfString(String string) {
        StringBuilder result = new StringBuilder("");
        try {
            String[] words = string.split("\\s");
            for (int i = 1; i <= 4 ; i++) {
                result.append(words[i]);
                result.append(" ");
            }
        } catch (Exception e) {
            throw new TooShortStringException();
        }
        return result.toString().trim();
    }

    public static class TooShortStringException extends RuntimeException {
    }
}
