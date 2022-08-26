package com.javarush.task.task18.task1828;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/* 
Прайсы 2
*/

public class Solution {
    public static void main(String[] args) {
        List<String> lines = new ArrayList<>();
        String fileName = "";
        try (BufferedReader console = new BufferedReader(new InputStreamReader(System.in))) {
            fileName = console.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (args.length > 1 && (args[0].equals("-u") || args[0].equals("-d"))) {

            try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
                while (reader.ready()) {
                    lines.add(reader.readLine());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try (FileWriter writer = new FileWriter(fileName)) {
                boolean hasLines = false;
                for (int i = 0; i < lines.size(); i++) {
                    String newLine = (hasLines) ? "\n" : "";
                    String line = lines.get(i);
                    String currId = line.substring(0, 8).trim();
                    if (currId.equals(args[1])) {
                        if ((args[0].equals("-u"))) {
                            String id = String.format("%-8s", currId);

                            String productName = "";
                            for (int j = 2; j < args.length - 2; j++) {
                                productName += args[j] + " ";
                            }
                            productName = (productName.length() > 30) ? productName.substring(0, 30) : productName.trim();
                            productName = String.format("%-30s", productName);

                            String price = args[args.length - 2];
                            if (price.length() > 8) {
                                price = price.substring(0, 8);
                            }
                            price = String.format("%-8s", price);

                            String quantity = args[args.length - 1];
                            if (quantity.length() > 4) {
                                quantity = quantity.substring(0, 4);
                            }
                            quantity = String.format("%-4s", quantity);
                            writer.write(newLine + id + productName + price + quantity);
                            hasLines = true;
                        }
                    } else {
                        writer.write(newLine + line);
                        hasLines = true;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }
}
