package com.javarush.task.task30.task3009;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

/* 
Палиндром?
*/

public class Solution {
    public static void main(String[] args) {
        System.out.println(getRadix("112"));        //expected output: [3, 27, 13, 15]
        System.out.println(getRadix("123"));        //expected output: [6]
        System.out.println(getRadix("5321"));       //expected output: []
        System.out.println(getRadix("1A"));         //expected output: []
    }

    private static Set<Integer> getRadix(String number) {
        Set<Integer> result = new HashSet<>();
        Integer value = null;
        try {
            value = Integer.parseInt(number);
        } catch (NumberFormatException ignore) {
        }

        if (value == null || value <= 0) {
            return result;
        }

        for (int i = 2; i <= 36; i++) {
            if (isPalindrom(convertNumberToAnotherBase(number, i))) {
                result.add(i);
            }
        }

        return result;
    }

    private static String convertNumberToAnotherBase(String number, int base) {
        BigInteger divider = BigInteger.valueOf(base);
        BigInteger value = new BigInteger(number);
        StringBuilder result = new StringBuilder();
        while (value.compareTo(BigInteger.ZERO) > 0) {
            int mod = value.mod(divider).intValue();
            result.insert(0, modToString(mod));
            value = value.divide(divider);
        }
        return result.toString();
    }

    private static String modToString(int mod) {
        return (mod < 10) ? String.valueOf(mod) : String.valueOf((char) (mod + 87));
    }

    private static boolean isPalindrom(String s) {
        return s.equals(new StringBuilder(s).reverse().toString());
    }
}