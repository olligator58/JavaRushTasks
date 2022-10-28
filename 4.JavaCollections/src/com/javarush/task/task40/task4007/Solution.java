package com.javarush.task.task40.task4007;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/* 
Работа с датами
*/

public class Solution {
    public static void main(String[] args) {
        printDate("21.4.2014 15:56:45");
        System.out.println();
        printDate("21.4.2014");
        System.out.println();
        printDate("17:33:40");
    }

    public static void printDate(String date) {
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(sdf1.parse(date));
            printOnlyDate(calendar);
            printOnlyTime(calendar);
            return;
        } catch (ParseException ignored) {
        }
        SimpleDateFormat sdf2 = new SimpleDateFormat("dd.MM.yyyy");
        try {
            calendar.setTime(sdf2.parse(date));
            printOnlyDate(calendar);
            return;
        } catch (ParseException ignored) {
        }
        SimpleDateFormat sdf3 = new SimpleDateFormat("HH:mm:ss");
        try {
            calendar.setTime(sdf3.parse(date));
            printOnlyTime(calendar);
        } catch (ParseException ignored) {
        }
    }

    private static void printOnlyDate(Calendar calendar) {
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        dayOfWeek = (dayOfWeek - 1 > 0) ? dayOfWeek - 1 : 7;
        System.out.println(String.format("День: %d", calendar.get(Calendar.DATE)));
        System.out.println(String.format("День недели: %d", dayOfWeek));
        System.out.println(String.format("День месяца: %d", calendar.get(Calendar.DAY_OF_MONTH)));
        System.out.println(String.format("День года: %d", calendar.get(Calendar.DAY_OF_YEAR)));
        System.out.println(String.format("Неделя месяца: %d", calendar.get(Calendar.WEEK_OF_MONTH)));
        System.out.println(String.format("Неделя года: %d", calendar.get(Calendar.WEEK_OF_YEAR)));
        System.out.println(String.format("Месяц: %d", calendar.get(Calendar.MONTH) + 1));
        System.out.println(String.format("Год: %d", calendar.get(Calendar.YEAR)));
    }

    private static void printOnlyTime(Calendar calendar) {
        String am = (calendar.get(Calendar.AM_PM) == Calendar.AM) ? "AM" : "PM";
        System.out.println(String.format("AM или PM: %s", am));
        System.out.println(String.format("Часы: %d", calendar.get(Calendar.HOUR)));
        System.out.println(String.format("Часы дня: %d", calendar.get(Calendar.HOUR_OF_DAY)));
        System.out.println(String.format("Минуты: %d", calendar.get(Calendar.MINUTE)));
        System.out.println(String.format("Секунды: %d", calendar.get(Calendar.SECOND)));
    }
}
