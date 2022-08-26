package com.javarush.task.task13.task1326;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/* 
Сортировка четных чисел из файла
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        List<Integer> numbers = new ArrayList();
        Scanner keyboard = new Scanner(System.in);
        FileInputStream fileInputStream = new FileInputStream(keyboard.nextLine());
        BufferedReader reader = new BufferedReader(new InputStreamReader(fileInputStream));

        while (reader.ready()) {
            Integer number = Integer.parseInt(reader.readLine());
            if (number % 2 == 0) {
                numbers.add(number);
            }
        }
        Collections.sort(numbers);
        for (Integer number: numbers) {
            System.out.println(number);
        }

        keyboard.close();
        fileInputStream.close();
        reader.close();
    }
}
