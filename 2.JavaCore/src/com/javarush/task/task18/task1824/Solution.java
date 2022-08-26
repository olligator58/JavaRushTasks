package com.javarush.task.task18.task1824;

import java.io.*;

/* 
Файлы и исключения
*/

public class Solution {
    public static void main(String[] args) {

        try (BufferedReader console = new BufferedReader(new InputStreamReader(System.in))) {

            while (true) {
                String fileName = console.readLine();

                try (FileReader reader = new FileReader(fileName)) {

                } catch (FileNotFoundException e) {
                    System.out.println(fileName);
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
