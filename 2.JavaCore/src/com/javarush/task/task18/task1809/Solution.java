package com.javarush.task.task18.task1809;

import java.io.*;

/* 
Реверс файла
*/

public class Solution {
    public static void main(String[] args) {
        String inputFileName = "";
        String outputFileName = "";

        try (BufferedReader console = new BufferedReader(new InputStreamReader(System.in))) {
            inputFileName = console.readLine();
            outputFileName = console.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (FileInputStream reader = new FileInputStream(inputFileName);
             FileOutputStream writer = new FileOutputStream(outputFileName)) {

            byte[] buffer = new byte[reader.available()];
            reader.read(buffer);

            for (int i = buffer.length - 1; i >= 0; i--) {
                writer.write(buffer[i]);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
