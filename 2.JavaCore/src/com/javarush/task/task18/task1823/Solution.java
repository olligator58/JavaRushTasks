package com.javarush.task.task18.task1823;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/* 
Нити и байты
*/

public class Solution {
    public static Map<String, Integer> resultMap = new HashMap<String, Integer>();

    public static void main(String[] args) {
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
        String fileName;
        try {
            while (!(fileName = console.readLine()).equals("exit")) {
                new ReadThread(fileName).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static class ReadThread extends Thread {
        private String fileName;

        public ReadThread(String fileName) {
            this.fileName = fileName;
        }

        @Override
        public void run() {
            int[] bytes = new int[256];
            int maxCounter = 0;
            int index = -1;

            try (FileInputStream reader = new FileInputStream(fileName)) {
                while (reader.available() > 0) {
                    int aByte = reader.read();
                    bytes[aByte] = ++bytes[aByte];
                    if (bytes[aByte] > maxCounter) {
                        maxCounter = bytes[aByte];
                        index = aByte;
                    }
                }
                if (index != -1) {
                    resultMap.put(fileName, index);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
