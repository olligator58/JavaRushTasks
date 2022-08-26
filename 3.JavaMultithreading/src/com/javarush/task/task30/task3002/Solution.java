package com.javarush.task.task30.task3002;

/* 
Осваиваем методы класса Integer
*/

public class Solution {

    public static void main(String[] args) {
        System.out.println(convertToDecimalSystem("0x16")); //22
        System.out.println(convertToDecimalSystem("012"));  //10
        System.out.println(convertToDecimalSystem("0b10")); //2
        System.out.println(convertToDecimalSystem("62"));   //62
    }

    public static String convertToDecimalSystem(String s) {
        String number = s.toLowerCase();
        int radix;
        if (number.contains("0x")) {
            radix = 16;
            number = number.replace("0x", "");
        } else if (number.contains("0b")) {
            radix = 2;
            number = number.replace("0b", "");
        } else if (number.startsWith("0")) {
            radix = 8;
        } else {
            radix = 10;
        }
        return String.valueOf(Integer.parseInt(number, radix));
    }
}
