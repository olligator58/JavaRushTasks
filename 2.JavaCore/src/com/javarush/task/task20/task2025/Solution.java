package com.javarush.task.task20.task2025;

import java.util.*;

/* 
Алгоритмы-числа
*/

public class Solution {

    public static long[] getNumbers(long N) {
        Set<Long> numbers = new TreeSet<>();

        if (N <= 0) {
            return new long[0];
        }

        int s = String.valueOf(N).length();
        long[][] pows = new long[10][20];
        initPows(pows);

        int[] digits = new int[s];
        for (int i = 0; i < s; i++) {
            nullDigits(digits);
            while (generateNextNumber(digits, i)) {
                long sum = calculatePowSums(digits, i, pows);
                long sum2 = calculatePowSums(sum, pows);
                if (sum2 == sum && sum < N) {
                    numbers.add(sum);
                }
            }
        }
        long[] result = new long[numbers.size()];
        int k = 0;
        for (Long number : numbers) {
            result[k] = number;
            k++;
        }
        numbers.clear();
        return result;
    }

    private static void initPows(long[][] pows) {
        for (int i = 0; i < pows.length; i++) {
            for (int j = 0; j < pows[i].length; j++) {
                long pow = i;
                for (int k = 0; k < j; k++) {
                    pow = pow * i;
                }
                pows[i][j] = pow;
            }
        }
    }

    private static boolean generateNextNumber(int[] digits, int maxPos) {
        int i = maxPos;
        digits[i]++;
        boolean needShift = false;
        while (digits[i] > 9) {
            if (i == 0) {
                return false;
            }
            i--;
            digits[i]++;
            needShift = true;
        }
        if (needShift) {
            Arrays.fill(digits, i + 1, maxPos + 1, digits[i]);
        }
        return true;
    }

    private static void nullDigits(int[] digits) {
        for (int i = 0; i < digits.length; i++) {
            digits[i] = 0;
        }
    }

    private static long calculatePowSums(int[] digits, int maxPos, long[][] pows) {
        long result = 0;
        for (int i = 0; i <= maxPos; i++) {
            result += pows[digits[i]][maxPos];
        }
        if (result < 0) {
            result = Long.MAX_VALUE;
        }
        return result;
    }

    private static long calculatePowSums(long number, long[][] pows) {
        String num = String.valueOf(number);
        int pow = num.length() - 1;
        long result = 0;
        for (int i = 0; i < num.length(); i++) {
            int d = num.charAt(i) - 48;
            result += pows[d][pow];
        }
        if (result < 0) {
            result = Long.MAX_VALUE;
        }
        return result;
    }

    //this method works too slowly
    /*private static int[] splitToDigits(long number) {
        String num = String.valueOf(number);
        int[] result = new int[num.length()];
        for (int i = 0; i < num.length(); i++) {
            result[i] = num.charAt(i) - 48;
        }
        return result;
    }*/


    //this method is just for testing
    private static void printNumber(int[] digits, int maxPos) {
        for (int i = 0; i <= maxPos; i++) {
            System.out.print(digits[i]);
        }
    }

    public static void main(String[] args) {
        long a = System.currentTimeMillis();
        System.out.println(Arrays.toString(getNumbers(Long.MAX_VALUE)));
        long b = System.currentTimeMillis();
        System.out.println("memory " + (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / (8 * 1024));
        System.out.println("time = " + (b - a) / 1000);

        a = System.currentTimeMillis();
        System.out.println(Arrays.toString(getNumbers(8)));
        b = System.currentTimeMillis();
        System.out.println("memory " + (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / (8 * 1024));
        System.out.println("time = " + (b - a) / 1000);
    }
}
