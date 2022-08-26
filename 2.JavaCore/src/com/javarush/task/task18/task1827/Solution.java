package com.javarush.task.task18.task1827;

import java.io.*;

/* 
Прайсы
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
        String fileName = console.readLine();
        console.close();

        if (args.length == 4 && args[0].equals("-c")) {
            int maxId = 0;
            try (BufferedReader reader = new BufferedReader(new FileReader(fileName));
                 PrintStream writer = new PrintStream(new FileOutputStream(fileName, true))) {

                while (reader.ready()) {
                    String line = reader.readLine();
                    int currId = Integer.parseInt(line.substring(0, 8).trim());
                    if (currId > maxId) {
                        maxId = currId;
                    }
                }
                maxId++;
                String id = String.valueOf(maxId);
                while (id.length() < 8) {
                    id = id + " ";
                }
                String productName = (args[1].length() > 30) ? args[1].substring(0, 30) : args[1];
                while (productName.length() < 30) {
                    productName = productName + " ";
                }
                String price = (args[2].length() > 8) ? args[2].substring(0, 8) : args[2];
                while (price.length() < 8) {
                    price = price + " ";
                }
                String quantity = (args[3].length() > 4) ? args[3].substring(0, 4) : args[3];
                while (quantity.length() < 4) {
                    quantity = quantity + " ";
                }
                writer.println();
                writer.print(id + productName + price + quantity);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
