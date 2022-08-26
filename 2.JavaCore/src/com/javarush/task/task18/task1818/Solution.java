package com.javarush.task.task18.task1818;

import java.io.*;
import java.nio.Buffer;

/* 
Два в одном
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

        try (FileInputStream reader = new FileInputStream(fileName2);
             FileOutputStream writer = new FileOutputStream(fileName1)) {
            byte[] buffer = new byte[1024];
            while (reader.available() > 0) {
                int count = reader.read(buffer);
                writer.write(buffer, 0, count);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (FileInputStream reader = new FileInputStream(fileName3);
             FileOutputStream writer = new FileOutputStream(fileName1, true)) {
            byte[] buffer = new byte[1024];
            while (reader.available() > 0) {
                int count = reader.read(buffer);
                writer.write(buffer, 0, count);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
