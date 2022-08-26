package com.javarush.task.task18.task1826;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/* 
Шифровка
*/

public class Solution {
    public static void main(String[] args) {
        int key = 1234567;
        if (args.length != 3) {
            throw new RuntimeException("Количество переданных параметров должно быть равно 3");
        }

        if (args[0].equals("-e") || args[0].equals("-d")) {
            try (FileInputStream input = new FileInputStream(args[1]);
                 FileOutputStream output = new FileOutputStream(args[2])) {

                while (input.available() > 0) {
                    int aByte = input.read();
                    output.write(aByte ^ key);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
