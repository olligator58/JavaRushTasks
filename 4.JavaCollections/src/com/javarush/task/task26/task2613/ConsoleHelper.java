package com.javarush.task.task26.task2613;

import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ResourceBundle;

public class ConsoleHelper {
    private static BufferedReader bis = new BufferedReader(new InputStreamReader(System.in));
    private static ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "common");

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() throws InterruptOperationException {
        try {
            String answer = bis.readLine();
            if (answer.toLowerCase().equals("exit")) {
                writeMessage(res.getString("the.end"));
                throw new InterruptOperationException();
            }
            return answer;
        } catch (IOException ignored) {
        }
        return null;
    }

    public static String askCurrencyCode() throws InterruptOperationException {
        writeMessage(res.getString("choose.currency.code"));
        String currencyCode;
        while ((currencyCode = readString()).length() != 3) {
            writeMessage(res.getString("invalid.data"));
        }
        return currencyCode.toUpperCase();
    }

    public static String[] getValidTwoDigits(String currencyCode) throws InterruptOperationException {
        writeMessage(String.format(res.getString("choose.denomination.and.count.format"), currencyCode.toUpperCase()));
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
                writeMessage(res.getString("invalid.data"));
            }
        }
    }

    public static Operation askOperation() throws InterruptOperationException {
        while (true) {
            writeMessage(res.getString("choose.operation"));
            writeMessage("1. " + res.getString("operation.INFO"));
            writeMessage("2. " + res.getString("operation.DEPOSIT"));
            writeMessage("3. " + res.getString("operation.WITHDRAW"));
            writeMessage("4. " + res.getString("operation.EXIT"));
            String answer = readString();
            try {
                return Operation.getAllowableOperationByOrdinal(Integer.parseInt(answer));
            } catch (Exception ignored) {
            }
        }
    }

    public static void printExitMessage() {
        writeMessage(res.getString("the.end"));
    }
}
