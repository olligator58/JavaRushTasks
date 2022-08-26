package com.javarush.task.task19.task1921;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/* 
Хуан Хуанович
*/

public class Solution {
    public static final List<Person> PEOPLE = new ArrayList<Person>();

    public static void main(String[] args) {
        if (args.length != 1) {
            throw new RuntimeException("В параметре должно быть передано имя файла");
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(args[0]))) {
            while (reader.ready()) {
                String[] fields = reader.readLine().split("\\s");
                String name = "";
                for (int i = 0; i < fields.length - 3; i++) {
                    name += fields[i] + " ";
                }
                int day = Integer.parseInt(fields[fields.length - 3]);
                int month = Integer.parseInt(fields[fields.length - 2]) - 1;
                int year = Integer.parseInt(fields[fields.length - 1]);
                Calendar date = new GregorianCalendar(year, month, day);
                PEOPLE.add(new Person(name.trim(), date.getTime()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
