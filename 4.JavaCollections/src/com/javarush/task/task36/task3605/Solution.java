package com.javarush.task.task36.task3605;

import java.io.*;
import java.util.Set;
import java.util.TreeSet;

/* 
Использование TreeSet
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            throw new RuntimeException("В параметре должно быть передано имя файла");
        }

        Set<Character> letters = new TreeSet<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(args[0])))) {
            while (reader.ready()) {
                String line = reader.readLine().toLowerCase();
                for (int i = 0; i < line.length(); i++) {
                    char c = line.charAt(i);
                    if (Character.isLetter(c)) {
                        letters.add(c);
                    }
                }
            }
        }
        int resultSize = Math.min(letters.size(), 5);
        int i = 0;
        for (Character letter : letters) {
            System.out.print(letter);
            i++;
            if (i >= resultSize) {
                break;
            }
        }
    }
}
