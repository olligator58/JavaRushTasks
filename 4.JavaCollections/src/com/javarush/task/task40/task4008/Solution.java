package com.javarush.task.task40.task4008;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.WeekFields;
import java.util.Locale;

/* 
Работа с Java 8 DateTime API
*/

public class Solution {
    public static void main(String[] args) {
        printDate("9.10.2017 5:56:45");
        System.out.println();
        printDate("21.4.2014");
        System.out.println();
        printDate("17:33:40");
    }

    public static void printDate(String date) {
        DateTimeFormatter dtfDate = DateTimeFormatter.ofPattern("d.M.yyyy");
        DateTimeFormatter dtfTime = DateTimeFormatter.ofPattern("H:m:s");
        boolean hasDate = date.contains(".");
        boolean hasTime = date.contains(":");
        String sDate = null;
        String sTime = null;
        if (hasDate && hasTime) {
            String[] fields = date.split("\\s");
            sDate = fields[0];
            sTime = fields[1];
        } else if (hasDate) {
            sDate = date;
        } else {
            sTime = date;
        }
        if (hasDate) {
            printOnlyDate(LocalDate.parse(sDate, dtfDate));
        }
        if (hasTime) {
            printOnlyTime(LocalTime.parse(sTime, dtfTime));
        }
    }

    private static void printOnlyDate(LocalDate date) {
        System.out.println(String.format("День: %d", date.getDayOfMonth()));
        System.out.println(String.format("День недели: %d", date.getDayOfWeek().getValue()));
        System.out.println(String.format("День месяца: %d", date.getDayOfMonth()));
        System.out.println(String.format("День года: %d", date.getDayOfYear()));
        System.out.println(String.format("Неделя месяца: %d", date.get(WeekFields.of(Locale.getDefault()).weekOfMonth())));
        System.out.println(String.format("Неделя года: %d", date.get(WeekFields.of(Locale.getDefault()).weekOfYear())));
        System.out.println(String.format("Месяц: %d", date.getMonthValue()));
        System.out.println(String.format("Год: %d", date.getYear()));
    }

    private static void printOnlyTime(LocalTime time) {
        String am = (time.get(ChronoField.AMPM_OF_DAY) == 0) ? "AM" : "PM";
        System.out.println(String.format("AM или PM: %s", am));
        System.out.println(String.format("Часы: %d", time.get(ChronoField.CLOCK_HOUR_OF_AMPM)));
        System.out.println(String.format("Часы дня: %d", time.getHour()));
        System.out.println(String.format("Минуты: %d", time.getMinute()));
        System.out.println(String.format("Секунды: %d", time.getSecond()));
    }
}
