package com.javarush.task.task13.task1319;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.IOException;

/* 
Писатель в файл с консоли
*/

public class Solution {
    public static void main(String[] args) throws IOException {

        InputStreamReader inputStreamReader = new InputStreamReader(System.in);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String fileName = bufferedReader.readLine();
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName));

        while (true) {
            String line = bufferedReader.readLine();
            bufferedWriter.write(line);
            bufferedWriter.newLine();
            if (line.equals("exit")) {
                break;
            }
        }

        inputStreamReader.close();
        bufferedReader.close();
        bufferedWriter.close();

    }
}
