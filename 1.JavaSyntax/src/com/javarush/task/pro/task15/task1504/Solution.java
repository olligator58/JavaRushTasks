package com.javarush.task.pro.task15.task1504;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

/* 
Перепутанные байты
*/

public class Solution {
    public static void main(String[] args) {

        try (Scanner keyboard = new Scanner(System.in);
             InputStream in = Files.newInputStream(Paths.get(keyboard.nextLine()));
             OutputStream out = Files.newOutputStream(Paths.get(keyboard.nextLine())) ) {

            byte[] buffer = new byte[2];
            while (in.available() > 0) {
                int real = in.read(buffer);
                if (real == 2) {
                    out.write(buffer[1]);
                    out.write(buffer[0]);
                } else {
                    out.write(buffer[0]);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

