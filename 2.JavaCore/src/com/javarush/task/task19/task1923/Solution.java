package com.javarush.task.task19.task1923;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/* 
Слова с цифрами
*/

public class Solution {
    public static void main(String[] args) {
        if (args.length != 2) {
            throw new RuntimeException("В параметрах должно быть передано 2 имя файла");
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(args[0]));
             FileWriter writer = new FileWriter(args[1])) {
            while (reader.ready()) {
                String[] words = reader.readLine().split("\\s");
                for (String word : words) {
                    if (!word.equals(word.replaceAll("\\d", ""))) {
                        writer.write(word + " ");
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
