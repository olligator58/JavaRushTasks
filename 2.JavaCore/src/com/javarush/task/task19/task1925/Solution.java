package com.javarush.task.task19.task1925;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/* 
Длинные слова
*/

public class Solution {
    public static void main(String[] args) {
        List<String> longWords = new ArrayList<>();

        if (args.length != 2) {
            throw new RuntimeException("В параметрах должны быть переданы 2 имени файла");
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(args[0]))) {
            while (reader.ready()) {
                String[] words = reader.readLine().split("\\s");
                for (String word : words) {
                    if (word.trim().length() > 6) {
                        longWords.add(word);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (FileWriter writer = new FileWriter(args[1])) {
            String comma = "";
            for (String longWord : longWords) {
                writer.write(comma + longWord);
                comma = ",";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
