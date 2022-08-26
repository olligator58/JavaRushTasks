package com.javarush.task.pro.task05.task0511;

import java.util.Scanner;

/* 
Создаем двумерный массив
*/

public class Solution {
    public static int[][] multiArray;

    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        int n = Integer.parseInt(keyboard.nextLine());
        multiArray = new int[n][];
        for (int i = 0; i < multiArray.length; i++) {
            int j = Integer.parseInt(keyboard.nextLine());
            multiArray[i] = new int[j];
        }
       /*вывод массива по условию не требуется
        for (int i = 0; i < multiArray.length ; i++) {
            for (int j = 0; j < multiArray[i].length; j++) {
                System.out.print("[]");
            }
            System.out.println();
        }*/
        keyboard.close();
    }
}
