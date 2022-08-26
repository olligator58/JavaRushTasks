package com.javarush.task.task19.task1910;

import java.io.*;

/* 
Пунктуация
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
             BufferedWriter writer = new BufferedWriter(new FileWriter(fileName2))) {
            while (reader.ready()) {
                String line = reader.readLine();
                writer.write(line.replaceAll("\\p{P}", ""));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
