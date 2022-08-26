package com.javarush.task.task18.task1821;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/* 
Встречаемость символов
*/

public class Solution {
    public static void main(String[] args) {
        HashMap<Integer, Integer> letters = new HashMap<>();

        if (args.length != 1) {
            throw new RuntimeException("В параметре должно быть передано имя файла");
        }

        try (FileReader reader = new FileReader(args[0]);) {
            while (reader.ready()) {
                int aChar = reader.read();
                if (!letters.containsKey(aChar)) {
                    letters.put(aChar, 1);
                } else {
                    int currCounter = letters.get(aChar);
                    currCounter++;
                    letters.replace(aChar, currCounter);
                }
            }
            List<Integer> sortedKeys = new ArrayList<>(letters.keySet());
            Collections.sort(sortedKeys);
            for (Integer sortedKey : sortedKeys) {
                System.out.print((char) ((int) sortedKey) + " " + letters.get(sortedKey));
                System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
