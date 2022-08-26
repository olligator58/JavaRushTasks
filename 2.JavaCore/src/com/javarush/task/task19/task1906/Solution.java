package com.javarush.task.task19.task1906;

import java.io.*;

/* 
Четные символы
*/

public class Solution {
    public static void main(String[] args) {
        String fileName1 = "";
        String fileName2 = "";

        try (BufferedReader console = new BufferedReader(new InputStreamReader(System.in))) {
            fileName1 = console.readLine();
            fileName2 = console.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (FileReader reader = new FileReader(fileName1);
             FileWriter writer = new FileWriter(fileName2)) {
            int i = 1;
            while (reader.ready()) {
                int aChar = reader.read();
                if (i % 2 == 0) {
                    writer.write(aChar);
                }
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
