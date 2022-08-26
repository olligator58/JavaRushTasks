package com.javarush.task.task14.task1420;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/* 
НОД
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int a = Integer.parseInt(reader.readLine());
        int b = Integer.parseInt(reader.readLine());
        int divider = 1;

        if (a > 0 && b > 0) {
            for (int i = Math.min(a, b); i >= 1; i--) {
                if (a % i == 0 && b % i == 0) {
                    divider = i;
                    break;
                }
            }
            System.out.println(divider);
        } else {
            System.out.println("Введены некорректные данные");
        }
    }
}
