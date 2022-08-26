package com.javarush.task.task22.task2212;

/* 
Проверка номера телефона
*/

public class Solution {
    public static boolean checkTelNumber(String telNumber) {
        boolean result = false;
        if (telNumber != null) {
            result = (telNumber.matches("^\\+(\\d[()]?){12}$") || telNumber.matches("^([()]?\\d){10}$")) &&
                     (telNumber.matches("^\\+?(\\d+)?(\\(\\d{3}\\))?\\d+$"));
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(String.format("%s - %b", null, checkTelNumber(null)));
        System.out.println(String.format("%s - %b", "", checkTelNumber("")));
        System.out.println(String.format("%s - %b", "+380501234567", checkTelNumber("+380501234567")));
        System.out.println(String.format("%s - %b", "+38(050)1234567", checkTelNumber("+38(050)1234567")));
        System.out.println(String.format("%s - %b", "(050)1234567", checkTelNumber("(050)1234567")));
        System.out.println(String.format("%s - %b", "0(501)234567", checkTelNumber("0(501)234567")));
        System.out.println(String.format("%s - %b", "+38)050(1234567", checkTelNumber("+38)050(1234567")));
        System.out.println(String.format("%s - %b", "+38(050)123-45-67", checkTelNumber("+38(050)123-45-67")));
        System.out.println(String.format("%s - %b", "050ххх4567", checkTelNumber("050ххх4567")));
        System.out.println(String.format("%s - %b", "050123456", checkTelNumber("050123456")));
        System.out.println(String.format("%s - %b", "(0)501234567", checkTelNumber("(0)501234567")));
    }
}
