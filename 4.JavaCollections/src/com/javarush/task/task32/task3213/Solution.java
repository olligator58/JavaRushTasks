package com.javarush.task.task32.task3213;

import java.io.IOException;
import java.io.StringReader;

/* 
Шифр Цезаря
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        StringReader reader = new StringReader("Khoor#Dpljr#&C,₷B'3");
        System.out.println(decode(reader, -3));  //Hello Amigo #@)₴?$0
    }

    public static String decode(StringReader reader, int key) throws IOException {
        StringBuilder result = new StringBuilder();
        if (reader == null) {
            return result.toString();
        }
        StringBuilder text = new StringBuilder();
        char[] buffer = new char[1024];
        int count;
        while ((count = reader.read(buffer)) > 0) {
            text.append(new String(buffer, 0, count));
        }
        for (int i = 0; i < text.length(); i++) {
            char symbol = (char) (text.charAt(i) + key);
            result.append(symbol);
        }
        return result.toString();
    }
}
