package com.javarush.task.task15.task1519;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* 
Разные методы для разных типов
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        String word;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (!(word = reader.readLine()).equals("exit")) {
            if (word.contains(".")) {
                try {
                    Double num = Double.parseDouble(word);
                    print(num);
                    continue;
                } catch (NumberFormatException e) {
//                    e.printStackTrace();
                }
            }

            try {
                int num = Integer.parseInt(word);
                if (num > 0 && num < 128) {
                    print((short) num);
                    continue;
                } else if (num <= 0 || num >= 128 ) {
                    print(num);
                    continue;
                }
            } catch (NumberFormatException e) {
//                e.printStackTrace();
            }

            print(word);
        }

        reader.close();
    }

    public static void print(Double value) {
        System.out.println("Это тип Double, значение " + value);
    }

    public static void print(String value) {
        System.out.println("Это тип String, значение " + value);
    }

    public static void print(short value) {
        System.out.println("Это тип short, значение " + value);
    }

    public static void print(Integer value) {
        System.out.println("Это тип Integer, значение " + value);
    }
}
