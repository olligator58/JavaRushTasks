package com.javarush.task.task18.task1804;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/* 
Самые редкие байты
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        HashMap<Integer, Integer> bytes = new HashMap<>();
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
        String fileName = console.readLine();
        console.close();

        try (FileInputStream reader = new FileInputStream(fileName);) {
            while (reader.available() > 0) {
                int aByte = reader.read();
                if (!bytes.keySet().contains(aByte)) {
                    bytes.put(aByte, 1);
                } else {
                    Integer currCounter = bytes.get(aByte);
                    currCounter++;
                    bytes.put(aByte, currCounter);
                }
            }
            Integer minCounter = bytes.values().stream().min((i1, i2) -> (i1 - i2)).get();
            for (Map.Entry<Integer, Integer> pair : bytes.entrySet()) {
                if (pair.getValue() == minCounter) {
                    System.out.print(pair.getKey() + " ");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
