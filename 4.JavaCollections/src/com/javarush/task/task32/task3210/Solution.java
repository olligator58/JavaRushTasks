package com.javarush.task.task32.task3210;

import java.io.IOException;
import java.io.RandomAccessFile;

/* 
Используем RandomAccessFile
*/

public class Solution {
    public static void main(String... args) {
        if (args.length != 3) {
            throw new RuntimeException("В программу должно быть передано 3 параметра");
        }

        String fileName = args[0];
        long number = Long.parseLong(args[1]);
        String text = args[2];

        int len = text.getBytes().length;
        try (RandomAccessFile raf = new RandomAccessFile(fileName, "rw")) {
            raf.seek(number);
            byte[] buffer = new byte[len];
            raf.read(buffer, 0, len);
            String readText = new String(buffer);
            raf.seek(raf.length());
            if (text.equals(readText)) {
                raf.write("true".getBytes());
            } else {
                raf.write("false".getBytes());
            }
        } catch (IOException ignore) {
        }
    }
}
