package com.javarush.task.task18.task1817;

import java.io.FileReader;
import java.io.IOException;

/* 
Пробелы
*/

public class Solution {
    public static void main(String[] args) {
        if (args.length != 1) {
            throw new RuntimeException("В параметре должно быть передано имя файла");
        }

        int allSymbolsNumber = 0;
        int spaceNumber = 0;
        try (FileReader reader = new FileReader(args[0])) {
            while (reader.ready()) {
                int aByte = reader.read();
                allSymbolsNumber++;
                if ((char) aByte == ' ') {
                    spaceNumber++;
                }
            }
            String result = String.format("%.2f",spaceNumber / (double) allSymbolsNumber * 100);
            result = result.replace(',', '.');
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
