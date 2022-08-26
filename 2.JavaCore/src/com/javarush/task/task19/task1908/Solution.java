package com.javarush.task.task19.task1908;

import java.io.*;

/* 
Выделяем числа
*/

public class Solution {
    public static void main(String[] args) {
        String fileName1 = "";
        String fileName2 = "";

        try (BufferedReader console = new BufferedReader(new InputStreamReader(System.in));) {
            fileName1 = console.readLine();
            fileName2 = console.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try ( BufferedReader reader = new BufferedReader(new FileReader(fileName1));
              BufferedWriter writer = new BufferedWriter(new FileWriter(fileName2))) {
            while (reader.ready()) {
                String line = reader.readLine();
                String[] words = line.split("\\s");
                for (String word : words) {
                    try {
                        int number = Integer.parseInt(word);
                        writer.write(number + " ");
                    } catch (NumberFormatException e) {
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
