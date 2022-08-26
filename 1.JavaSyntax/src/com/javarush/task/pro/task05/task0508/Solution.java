package com.javarush.task.pro.task05.task0508;

import java.util.Scanner;

/*
Удаляем одинаковые строки
*/

public class Solution {
    public static String[] strings;
    public static final int LINES_NUMBER = 6;

    public static void main(String[] args) {
        //напишите тут ваш код
        Scanner keyboard = new Scanner(System.in);
        strings = new String[LINES_NUMBER];
        //ввод массива
        for (int i = 0; i < strings.length; i++) {
            strings[i] = keyboard.nextLine();
        }
        //убираем повторяющиеся строки
        for (int i = 0; i < strings.length; i++) {
            if (strings[i] != null) {
                //String line = strings[i];
                boolean found = false;
                for (int j = i + 1; j < strings.length; j++) {
                    if (strings[j] != null && strings[j].equals(strings[i])) { // в остатке массива ищем повторяющиеся строки и обнуляем их
                        strings[j] = null;
                        found = true; // нашли повторяющуюся строку
                    }
                }
                if (found) {
                    strings[i] = null; // если в остатке массива нашли повторяющуюся строку, то обнуляем текущий элемент массива
                }
            }
        }
        //вывод массива
        for (int i = 0; i < strings.length; i++) {
            System.out.print(strings[i] + ", ");
        }
        keyboard.close();
    }
}
