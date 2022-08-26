package com.javarush.task.task19.task1904;

import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

/* 
И еще один адаптер
*/

public class Solution {

    public static void main(String[] args) {

    }

    public static class PersonScannerAdapter implements PersonScanner {
        private Scanner fileScanner;

        public PersonScannerAdapter(Scanner fileScanner) {
            this.fileScanner = fileScanner;
        }

        @Override
        public Person read() throws IOException {
            String line = fileScanner.nextLine();
            String[] fields = line.split("\\s");
            int year = Integer.parseInt(fields[5]);
            int month = Integer.parseInt(fields[4]) - 1;
            int day = Integer.parseInt(fields[3]);
            Calendar birthDate = new GregorianCalendar(year, month, day);
            return new Person(fields[1], fields[2], fields[0], birthDate.getTime());
        }

        @Override
        public void close() throws IOException {
            fileScanner.close();
        }
    }
}
