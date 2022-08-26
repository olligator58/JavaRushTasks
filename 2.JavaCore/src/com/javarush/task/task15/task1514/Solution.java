package com.javarush.task.task15.task1514;

import java.util.HashMap;
import java.util.Map;

/* 
Статики-1
*/

public class Solution {
    public static Map<Double, String> labels = new HashMap<Double, String>();

    static {
        labels.put(3.14, "Pi");
        labels.put(5.0, "Five");
        labels.put(23.34, "Twenty three");
        labels.put(50.35, "Fifty");
        labels.put(94.78, "Quatre vingt quatorze");
    }

    public static void main(String[] args) {
        System.out.println(labels);
    }
}
