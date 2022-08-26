package com.javarush.task.task18.task1819;

import java.io.*;

/* 
Объединение файлов
*/

public class Solution {
    public static void main(String[] args) {
        String fileName1 = "";
        String fileName2 = "";
        byte[] buffer = null;

        try (BufferedReader console = new BufferedReader(new InputStreamReader(System.in));) {
            fileName1 = console.readLine();
            fileName2 = console.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (FileInputStream reader = new FileInputStream(fileName1)) {
            buffer = new byte[reader.available()];
            reader.read(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (FileInputStream reader = new FileInputStream(fileName2);
             FileOutputStream writer = new FileOutputStream(fileName1)) {
            byte[] buffer1 = new byte[1024];
            while (reader.available() > 0) {
                int count = reader.read(buffer1);
                writer.write(buffer1, 0, count);
            }
            writer.write(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
