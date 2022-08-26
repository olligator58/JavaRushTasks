package com.javarush.task.task18.task1803;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* 
Самые частые байты
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        ArrayList<Integer> bytes = new ArrayList<>();
        ArrayList<Integer> counters = new ArrayList<>();
        int maxCounter = 0;

        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
        String fileName = console.readLine();
        console.close();

        try (FileInputStream reader = new FileInputStream(fileName)) {
            while (reader.available() > 0) {
                Integer aByte = reader.read();
                int currCounter;
                if (!bytes.contains(aByte)) {
                    bytes.add(aByte);
                    counters.add(1);
                    currCounter = 1;
                } else {
                    currCounter = counters.get(bytes.indexOf(aByte));
                    currCounter++;
                    counters.set(bytes.indexOf(aByte), currCounter);
                }
                if (currCounter > maxCounter) {
                    maxCounter = currCounter;
                }
            }

            for (int i = 0; i < counters.size(); i++) {
                if (counters.get(i) == maxCounter) {
                    System.out.print(bytes.get(i) + " ");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
