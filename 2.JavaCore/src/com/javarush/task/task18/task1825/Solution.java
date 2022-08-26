package com.javarush.task.task18.task1825;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;
import java.util.TreeSet;

/* 
Собираем файл
*/

public class Solution {
    public static void main(String[] args) {
        Set<String> partNames = new TreeSet<>((s1, s2) -> {
            int num1 = Integer.parseInt(s1.substring(s1.lastIndexOf('.') + 5));
            int num2 = Integer.parseInt(s2.substring(s2.lastIndexOf('.') + 5));
            return num1 - num2;
        });

        try (BufferedReader console = new BufferedReader(new InputStreamReader(System.in))) {
            String fileName;
            while (!(fileName = console.readLine()).equals("end")) {
                partNames.add(fileName);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        String fileName = partNames.stream().findFirst().get();
        Path dir = Paths.get(fileName).getParent();
        Path file = Paths.get(fileName.substring(0, fileName.lastIndexOf('.')));
        fileName = dir.resolve(file).toString();

        try (FileOutputStream writer = new FileOutputStream(fileName)) {
            for (String partName : partNames) {
                try (FileInputStream reader = new FileInputStream(partName)) {
                    while (reader.available() > 0) {
                        byte[] buffer = new byte[1024];
                        int count = reader.read(buffer);
                        writer.write(buffer, 0, count);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
