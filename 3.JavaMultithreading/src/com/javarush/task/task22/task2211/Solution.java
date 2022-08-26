package com.javarush.task.task22.task2211;

import java.io.*;
import java.nio.charset.Charset;

/* 
Смена кодировки
*/

public class Solution {
    public static void main(String[] args) throws IOException {

        Charset sourceCharset = Charset.forName("Windows-1251");
        Charset destCharset = Charset.forName("UTF-8");

        try (FileInputStream reader = new FileInputStream(args[0]);
             FileOutputStream writer = new FileOutputStream(args[1]);) {
            byte[] buffer = new byte[1000];
            while (reader.available() > 0) {
                int count = reader.read(buffer);
                String block = new String(buffer, 0, count, sourceCharset);
                writer.write(block.getBytes(destCharset));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
