package com.javarush.task.task18.task1816;

import java.io.FileReader;
import java.io.IOException;

/* 
Английские буквы
*/

public class Solution {
    public static void main(String[] args) {
        if (args.length != 1) {
            throw new RuntimeException("В параметрах должно быть передано имя файла");
        }

        int lettersCounter = 0;
        try (FileReader reader = new FileReader(args[0])) {
            while (reader.ready()) {
                int aByte = reader.read();
                if ((aByte >= 65 && aByte <= 90) || (aByte >= 97 && aByte <= 122)) {
                    lettersCounter++;
                }
            }
            System.out.println(lettersCounter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
