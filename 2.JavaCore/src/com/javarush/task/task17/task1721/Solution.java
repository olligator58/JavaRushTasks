package com.javarush.task.task17.task1721;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* 
Транзакционность
*/

public class Solution {
    public static List<String> allLines = new ArrayList<String>();
    public static List<String> forRemoveLines = new ArrayList<String>();

    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String fileName1 = reader.readLine();
            String fileName2 = reader.readLine();
            reader.close();

            BufferedReader reader1 = new BufferedReader(new FileReader(fileName1));
            BufferedReader reader2 = new BufferedReader(new FileReader(fileName2));

            while (reader1.ready()) {
                String line = reader1.readLine();
                allLines.add(line);
            }

            while (reader2.ready()) {
                String line = reader2.readLine();
                forRemoveLines.add(line);
            }

            reader1.close();
            reader2.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            new Solution().joinData();
        } catch (CorruptedDataException e) {
            e.printStackTrace();
        }

        /*for (String line: allLines) {
            System.out.println(line);
        }
        System.out.println();
        for (String line: forRemoveLines) {
            System.out.println(line);
        }*/

    }

    public void joinData() throws CorruptedDataException {
        for (String line: forRemoveLines) {
            if (allLines.contains(line)) {
                allLines.remove(line);
            } else {
                allLines.clear();
                throw new CorruptedDataException();
            }
        }
    }
}
