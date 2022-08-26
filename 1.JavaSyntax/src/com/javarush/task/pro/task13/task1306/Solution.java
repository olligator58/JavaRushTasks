package com.javarush.task.pro.task13.task1306;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/* 
Изучаем методы класса Collections, часть 1
*/

public class Solution {

    public static void copy(ArrayList<String> destination, ArrayList<String> source) {
        Collections.copy(destination, source);
    }

    public static void addAll(ArrayList<String> list, String... strings) {
        Collections.addAll(list, strings);
    }

    public static void replaceAll(ArrayList<String> list, String oldValue, String newValue) {
        Collections.replaceAll(list, oldValue, newValue);
    }

    public static void main(String[] args) {
        ArrayList<String> listFirst = new ArrayList<>(Arrays.asList("1 2 3 4 5 6 7 8".split(" ")));
        ArrayList<String> listSecond = new ArrayList<>(Arrays.asList("10 11 12 13 14".split(" ")));
        copy(listFirst, listSecond);
        addAll(listFirst, "uno", "tuno", "tres", "quatro");
        replaceAll(listFirst, "tres", "trois");
        System.out.println(listFirst);
    }

}
