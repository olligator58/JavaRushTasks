package com.javarush.task.task26.task2613;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleHelper {
    private static BufferedReader bis = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() {
        try {
            return bis.readLine();
        } catch (IOException ignored) {
        }
        return null;
    }

    public static String askCurrencyCode() {
        writeMessage("Введите код валюты:");
        String currencyCode;
        while ((currencyCode = readString()).length() != 3) {
            writeMessage("Код валюты должен иметь 3 символа");
        }
        return currencyCode.toUpperCase();
    }

    public static String[] getValidTwoDigits(String currencyCode) {
        writeMessage(String.format("Введите номинал и количество банкнот для валюты %s:", currencyCode.toUpperCase()));
        boolean numbersCorrect = false;
        while (true) {
            String[] numbers = readString().split("\\s");
            if (numbers.length == 2) {
                numbersCorrect = true;
                try {
                    int n1 = Integer.parseInt(numbers[0]);
                    int n2 = Integer.parseInt(numbers[1]);
                    if (n1 <= 0 || n2 <= 0) {
                        numbersCorrect = false;
                    }
                } catch (NumberFormatException e) {
                    numbersCorrect = false;
                }
            }
            if (numbersCorrect) {
                return numbers;
            } else {
                writeMessage("Нужно ввести 2 целых положительных числа, разделенные пробелом");
            }
        }
    }

    public static Operation askOperation() {
        while (true) {
            writeMessage("Введите код операции:");
            writeMessage("1. INFO");
            writeMessage("2. DEPOSIT");
            writeMessage("3. WITHDRAW");
            writeMessage("4. EXIT");
            String answer = readString();
            try {
                return Operation.getAllowableOperationByOrdinal(Integer.parseInt(answer));
            } catch (Exception ignored) {
            }
        }
    }
}
