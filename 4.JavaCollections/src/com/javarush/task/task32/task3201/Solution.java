package com.javarush.task.task32.task3201;

import java.io.IOException;
import java.io.RandomAccessFile;

/* 
Запись в существующий файл
*/

public class Solution {
    public static void main(String... args) {
        if (args.length != 3) {
            throw new RuntimeException("В программу должно быть передано 3 параметра");
        }

        String fileName = args[0];
        long number = Long.parseLong(args[1]);
        String text = args[2];

        try (RandomAccessFile raf = new RandomAccessFile(fileName, "rw")) {
            long pos = Math.min(number, raf.length());
            raf.seek(pos);
            raf.write(text.getBytes());
        } catch (IOException ignore) {
        }
    }
}
