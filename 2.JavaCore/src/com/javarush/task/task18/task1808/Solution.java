package com.javarush.task.task18.task1808;

import java.io.*;

/* 
Разделение файла
*/

public class Solution {
    public static void main(String[] args) {
        String fileName1 = "";
        String fileName2 = "";
        String fileName3 = "";

        try (BufferedReader console = new BufferedReader(new InputStreamReader(System.in))) {
            fileName1 = console.readLine();
            fileName2 = console.readLine();
            fileName3 = console.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (FileInputStream reader = new FileInputStream(fileName1);
             FileOutputStream writer1 = new FileOutputStream(fileName2);
             FileOutputStream writer2 = new FileOutputStream(fileName3)) {

            int firstSize = (reader.available() % 2 == 0) ? reader.available() / 2 : reader.available() / 2 + 1;
            byte[] buffer = new byte[firstSize];
            reader.read(buffer);
            writer1.write(buffer);
            int count = reader.read(buffer);
            writer2.write(buffer, 0, count);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
