package com.javarush.task.pro.task05.task0505;

import java.util.Scanner;

/* 
Reverse
*/

public class Solution {
    public static void main(String[] args) {
        //напишите тут ваш код
        Scanner keyboard = new Scanner(System.in);
        int[] Array;
        int n = keyboard.nextInt();
        if (n > 0) {
            Array = new int[n];
            //ввод массива
            for (int i = 0; i < n; i++) {
                Array[i] = keyboard.nextInt();
            }
            if (n % 2 == 0) { //N-четное, выводим числа в обратном порядке
                for (int i = n - 1; i >= 0; i--) {
                    System.out.println(Array[i]);
                }
            } else { //N-нечетное, выводим числа в порядке ввода
                for (int i = 0; i < n; i++) {
                    System.out.println(Array[i]);
                }
            }
        }
        keyboard.close();
    }
}