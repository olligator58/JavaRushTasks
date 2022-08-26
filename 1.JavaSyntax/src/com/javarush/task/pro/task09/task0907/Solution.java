package com.javarush.task.pro.task09.task0907;

/* 
Шестнадцатеричный конвертер
*/

public class Solution {
    private static final String HEX = "0123456789abcdef";

    public static void main(String[] args) {
        int decimalNumber = 1256;
        System.out.println("Десятичное число " + decimalNumber + " равно шестнадцатеричному числу " + toHex(decimalNumber));
        String hexNumber = "4e8";
        System.out.println("Шестнадцатеричное число " + hexNumber + " равно десятичному числу " + toDecimal(hexNumber));
    }

    public static String toHex(int decimalNumber) {
        String hexString = "";
        if (decimalNumber <= 0) {
            return hexString;
        }
        while (decimalNumber != 0) {
            hexString = HEX.charAt(decimalNumber % 16) + hexString;
            decimalNumber = decimalNumber / 16;
        }
        return hexString;
    }

    public static int toDecimal(String hexNumber) {
        int decimal = 0;
        if (hexNumber == null || hexNumber.equals("")) {
            return decimal;
        }
        for (int i = 0; i < hexNumber.length(); i++) {
            int index = HEX.indexOf(hexNumber.charAt(i));
            decimal = 16 * decimal + index;
        }
        return decimal;
    }
}
