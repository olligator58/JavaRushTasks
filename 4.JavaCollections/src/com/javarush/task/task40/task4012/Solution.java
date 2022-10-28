package com.javarush.task.task40.task4012;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;

/* 
Полезные методы DateTime API
*/

public class Solution {
    public static void main(String[] args) {
        LocalDate date1 = LocalDate.of(2024, 10, 25);
        LocalDate date2 = LocalDate.of(2022, 10, 20);
        LocalDateTime ldt = LocalDateTime.of(2022, 10, 26, 12, 55, 10);
        LocalTime time = LocalTime.now();
        System.out.println(isLeap(date1));
        System.out.println(isBefore(ldt));
        System.out.println(addTime(time, 10, ChronoUnit.MINUTES));
        System.out.println(getPeriodBetween(date1, date2));
    }

    public static boolean isLeap(LocalDate date) {
        return date.isLeapYear();
    }

    public static boolean isBefore(LocalDateTime dateTime) {
        return dateTime.isBefore(LocalDateTime.now());
    }

    public static LocalTime addTime(LocalTime time, int n, ChronoUnit chronoUnit) {
        return time.plus(n, chronoUnit);
    }

    public static Period getPeriodBetween(LocalDate firstDate, LocalDate secondDate) {
        return (firstDate.isBefore(secondDate)) ? Period.between(firstDate, secondDate) : Period.between(secondDate, firstDate);
    }
}
