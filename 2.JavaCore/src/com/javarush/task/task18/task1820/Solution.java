package com.javarush.task.task18.task1820;

import java.io.*;

/* 
Округление чисел
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

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName1));
             FileWriter writer = new FileWriter(fileName2)) {

            String line = reader.readLine();
            String[] values = line.split("\\s");
            for (int i = 0; i < values.length; i++) {
                writer.write(Math.round(Double.parseDouble(values[i])) + " ");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
