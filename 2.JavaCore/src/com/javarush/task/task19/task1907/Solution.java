package com.javarush.task.task19.task1907;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* 
Считаем слово
*/

public class Solution {
    public static void main(String[] args) {
        String fileName = "";

        try (BufferedReader console = new BufferedReader(new InputStreamReader(System.in));) {
            fileName = console.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String line = "";
        try (FileReader reader = new FileReader(fileName)) {
            while (reader.ready()) {
                int aChar = reader.read();
                line += (char) aChar;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] words = line.split("\\W");
        int worldCounter = 0;
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals("world")) {
                worldCounter++;
            }
        }
        System.out.println(worldCounter);
    }
}
