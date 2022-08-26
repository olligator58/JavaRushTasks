package com.javarush.task.task18.task1805;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TreeSet;

/* 
Сортировка байт
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        TreeSet<Integer> treeSet = new TreeSet<>();
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
        String fileName = console.readLine();
        console.close();

        try (FileInputStream reader = new FileInputStream(fileName)) {
            while (reader.available() > 0) {
                treeSet.add(reader.read());
            }
            for (Integer aByte : treeSet) {
                System.out.print(aByte + " ");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
