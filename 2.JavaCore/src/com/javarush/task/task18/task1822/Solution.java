package com.javarush.task.task18.task1822;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* 
Поиск данных внутри файла
*/

public class Solution {
    public static void main(String[] args) {
        String fileName = "";
        if (args.length != 1) {
            throw new RuntimeException("Параметром должен быть передан id строки");
        }

        try (BufferedReader console = new BufferedReader(new InputStreamReader(System.in));) {
            fileName = console.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName));) {
            while (reader.ready()) {
                String line = reader.readLine();
                String id = line.substring(0, line.indexOf(" "));
                if (id.equals(args[0])) {
                    System.out.println(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
