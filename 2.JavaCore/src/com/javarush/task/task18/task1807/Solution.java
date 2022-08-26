package com.javarush.task.task18.task1807;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/* 
Подсчет запятых
*/

public class Solution {
    public static void main(String[] args) {
        int commasCounter = 0;
        try (BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
             FileInputStream reader = new FileInputStream(console.readLine())) {

            char comma = ',';
            while (reader.available() > 0) {
                int aByte = reader.read();
                if (aByte == comma) {
                    commasCounter++;
                }
            }
            System.out.println(commasCounter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
