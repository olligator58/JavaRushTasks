package com.javarush.task.task19.task1920;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

/* 
Самый богатый
*/

public class Solution {
    public static void main(String[] args) {
        TreeMap<String, Double> salaries = new TreeMap<>();

        if (args.length != 1) {
            throw new RuntimeException("В параметре должно быть передано имя файла");
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(args[0]));) {
            while (reader.ready()) {
                String[] fields = reader.readLine().split("\\s");
                String name = fields[0];
                Double amount = Double.parseDouble(fields[1]);
                if (!salaries.containsKey(name)) {
                    salaries.put(name, amount);
                } else {
                    amount += salaries.get(name);
                    salaries.replace(name, amount);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        double maxSalary = salaries.firstEntry().getValue();
        for (double value : salaries.values()) {
            if (value > maxSalary) {
                maxSalary = value;
            }
        }

        for (Map.Entry<String, Double> salary: salaries.entrySet()) {
            if (salary.getValue() == maxSalary) {
                System.out.println(salary.getKey());
            }
        }
    }
}
