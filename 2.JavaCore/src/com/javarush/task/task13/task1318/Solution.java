package com.javarush.task.task13.task1318;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/* 
Чтение файла
*/

public class Solution {
    public static void main(String[] args) throws IOException {

        Scanner keyboard = new Scanner(System.in);
        FileInputStream inputStream = new FileInputStream(keyboard.nextLine());
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        while (bufferedReader.ready()) {
            String line = bufferedReader.readLine();
            System.out.println(line);
        }

        keyboard.close();
        inputStream.close();
        bufferedReader.close();
    }
}