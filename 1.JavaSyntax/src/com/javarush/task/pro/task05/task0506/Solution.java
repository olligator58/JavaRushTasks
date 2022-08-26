package com.javarush.task.pro.task05.task0506;

import java.util.Scanner;

/* 
Минимальное из N чисел
*/

public class Solution {
    public static int[] array;

    public static void main(String[] args) throws Exception {
        //напишите тут ваш код
        Scanner keyboard = new Scanner(System.in);
        int n = keyboard.nextInt();
        array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = keyboard.nextInt();
        }
        int min = array[0];
        for (int i = 1; i < n; i++) {
            if (array[i] < min) {
                min = array[i];
            }
        }
        System.out.println(min);
        keyboard.close();
    }
}
