package com.javarush.task.pro.task09.task0908;

/*
Двоично-шестнадцатеричный конвертер
*/

public class Solution {
    public static final String[] BINS = {"0000", "0001", "0010", "0011", "0100", "0101", "0110", "0111",
                                         "1000", "1001", "1010", "1011", "1100", "1101", "1110", "1111"};
    public static final String HEX = "0123456789abcdef";

    public static void main(String[] args) {
        String binaryNumber = "100111010000";
        System.out.println("Двоичное число " + binaryNumber + " равно шестнадцатеричному числу " + toHex(binaryNumber));
        String hexNumber = "9d0";
        System.out.println("Шестнадцатеричное число " + hexNumber + " равно двоичному числу " + toBinary(hexNumber));
    }

    public static String toHex(String binaryNumber) {
        String hexNum = "";
        // проверка, что строка не null и непустая
        if (binaryNumber == null || binaryNumber.equals("")) {
            return hexNum;
        }
        // проверка, что строка содержит только 1 и 0
        for (int i = 0; i < binaryNumber.length(); i++) {
            if (binaryNumber.charAt(i) != '0' && binaryNumber.charAt(i) != '1') {
                return hexNum;
            }
        }
        //дополняем строку нулями, чтобы она была кратной 4
        if (binaryNumber.length() % 4 != 0) {
            int nullsNumber = 4 - binaryNumber.length() % 4;
            for (int i = 0; i < nullsNumber; i++) {
                binaryNumber = '0' + binaryNumber;
            }
        }
        // берем по 4 символа в строке и переводим их в шестнадцатиричное число
        int i = 0, j = 3;
        while (j < binaryNumber.length()) {
            String fourDigits = binaryNumber.substring(i, j + 1);
            //ищем наши 4 цифры в массиве BINS
            int index = 0;
            for (int k = 0; k < BINS.length; k++) {
                if (BINS[k].equals(fourDigits)) {
                    index = k;
                    break;
                }
            }
            hexNum = hexNum + HEX.charAt(index);
            i += 4;
            j += 4;
        }
        return hexNum;
    }

    public static String toBinary(String hexNumber) {
        String binNum = "";
        // проверка, что строка не null и непустая
        if (hexNumber == null || hexNumber.equals("")) {
            return binNum;
        }
        // проверка, что строка содержит только символы из HEX
        for (int i = 0; i < hexNumber.length(); i++) {
            if (HEX.indexOf(hexNumber.charAt(i)) < 0) {
                return binNum;
            }
        }
        // ищем соответствующую символам двоичную строку
        for (int i = 0; i < hexNumber.length(); i++) {
            binNum = binNum + BINS[HEX.indexOf(hexNumber.charAt(i))];
        }
        return binNum;
    }
}
