package com.javarush.task.pro.task13.task1317;

import java.util.Arrays;

public class Solution {
    public static void main(String[] args) {
        System.out.println(Month.JANUARY);
        Month[] array = Month.values();
        System.out.println(Arrays.toString(array));
        System.out.println(Month.JANUARY.ordinal());
    }
}
