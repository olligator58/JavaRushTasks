package com.javarush.task.pro.task16.task1601;

import java.util.Date;

/* 
Лишь бы не в понедельник :)
*/

public class Solution {

    static Date birthDate = new Date(122, 0, 23);

    public static void main(String[] args) {
        System.out.println(getDayOfWeek(birthDate));
    }

    static String getDayOfWeek(Date date) {
        int day = date.getDay();
        String result;
        switch (day) {
            case 0:
                return "воскресенье";
            case 1:
                result = "понедельник";
                break;
            case 2:
                result = "вторник";
                break;
            case 3:
                result = "среда";
                break;
            case 4:
                result = "четверг";
                break;
            case 5:
                result = "пятница";
                break;
            case 6:
                result = "суббота";
                break;
            default:
                result = null;
        }
        return result;
    }
}
