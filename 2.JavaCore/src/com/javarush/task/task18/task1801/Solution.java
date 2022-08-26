package com.javarush.task.task18.task1801;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/* 
Максимальный байт
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        int max = Integer.MIN_VALUE;
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
        String fileName = console.readLine();
        console.close();

        FileInputStream reader = new FileInputStream(fileName);
        while (reader.available() > 0) {
            int aByte = reader.read();
            if (aByte > max) {
                max = aByte;
            }
        }
        reader.close();
        System.out.println(max);
    }
}
