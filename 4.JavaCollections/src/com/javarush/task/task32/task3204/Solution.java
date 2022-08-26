package com.javarush.task.task32.task3204;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

/* 
Генератор паролей
*/

public class Solution {
    public static void main(String[] args) {

        ByteArrayOutputStream password = getPassword();
        System.out.println(password.toString());
    }

    public static ByteArrayOutputStream getPassword() {
        final int PASSWORD_LENGTH = 8;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        char[] password = new char[PASSWORD_LENGTH];

        char aDigit = generateRandomDigit();
        placeSymbolToFreePosition(password, aDigit);

        char aLowerCaseLetter = generateRandomLowerCaseLetter();
        placeSymbolToFreePosition(password, aLowerCaseLetter);

        char aUpperCaseLetter = generateRandomUpperCaseLetter();
        placeSymbolToFreePosition(password, aUpperCaseLetter);

        int restPositions = calculateFreePositions(password);

        for (int i = 0; i < restPositions; i++) {
            placeSymbolToFreePosition(password, generateRandomDigitOrLetter());
        }

        for (char c : password) {
            baos.write(c);
        }
        return baos;
    }

    private static void placeSymbolToFreePosition(char[] password, char symbol) {
        List<Integer> freePositions = new ArrayList<>();
        for (int i = 0; i < password.length; i++) {
            if (password[i] == 0) {
                freePositions.add(i);
            }
        }
        if (freePositions.size() > 0) {
            int randomFreePosition = freePositions.get(generateRandomNumber(freePositions.size()));
            password[randomFreePosition] = symbol;
        }
    }

    private static int generateRandomNumber(int upperBoard) {
        return (int) (Math.random() * 99) % upperBoard;
    }

    private static char generateRandomDigit() {
        return (char) ('0' + generateRandomNumber(10));
    }

    private static char generateRandomLowerCaseLetter() {
        return (char) ('a' + generateRandomNumber(26));
    }

    private static char generateRandomUpperCaseLetter() {
        return (char) ('A' + generateRandomNumber(26));
    }

    private static char generateRandomLetter() {
        return (generateRandomNumber(2) == 0) ? generateRandomLowerCaseLetter() : generateRandomUpperCaseLetter();
    }

    private static char generateRandomDigitOrLetter() {
        return (generateRandomNumber(2) == 0) ? generateRandomDigit() : generateRandomLetter();
    }

    private static int calculateFreePositions(char[] password) {
        int counter = 0;
        for (char c : password) {
            if (c == 0) {
                counter++;
            }
        }
        return counter;
    }
}
