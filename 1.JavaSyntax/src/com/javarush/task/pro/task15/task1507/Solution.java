package com.javarush.task.pro.task15.task1507;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

/* 
Пропускаем не всех
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        try (Scanner keyboard = new Scanner(System.in)) {
            ArrayList<String> lines = (ArrayList<String>) Files.readAllLines(Paths.get(keyboard.nextLine()));
            for (int i = 0; i < lines.size(); i++) {
                if (i % 2 == 0) {
                    System.out.println(lines.get(i));
                }
            }
        }
    }
}

