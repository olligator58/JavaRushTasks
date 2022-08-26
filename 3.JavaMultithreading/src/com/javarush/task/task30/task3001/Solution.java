package com.javarush.task.task30.task3001;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* 
Конвертер систем счислений
*/

public class Solution {
    private static final List<Character> LETTERS = Arrays.asList('a', 'b', 'c', 'd', 'e', 'f');

    public static void main(String[] args) {
        Number number = new Number(NumberSystemType._10, "6");
        Number result = convertNumberToOtherNumberSystem(number, NumberSystemType._2);
        System.out.println(result);    //expected 110

        number = new Number(NumberSystemType._16, "6df");
        result = convertNumberToOtherNumberSystem(number, NumberSystemType._8);
        System.out.println(result);    //expected 3337

        number = new Number(NumberSystemType._16, "abcdefabcdef");
        result = convertNumberToOtherNumberSystem(number, NumberSystemType._16);
        System.out.println(result);    //expected abcdefabcdef

    }

    public static Number convertNumberToOtherNumberSystem(Number number, NumberSystem expectedNumberSystem) {
        checkNumber(number);
        BigInteger result = calcNumberInTensSystem(number);
        return numberInExpected(result, expectedNumberSystem);
    }

    private static void checkNumber(Number number) {
        int base = number.getNumberSystem().getNumberSystemIntValue();
        String digit = number.getDigit();
        if (base <= 10) {
            checkDigits(digit, base);
        } else {
            checkDigitsAndLetters(digit, base);
        }
    }

    private static void checkDigits(String digit, int base) {
        for (int i = 0; i < digit.length(); i++) {
            char symbol = digit.charAt(i);
            if (!Character.isDigit(symbol) || symbol - 48 > base - 1) {
                throw new NumberFormatException();
            }
        }
    }

    private static void checkDigitsAndLetters(String digit, int base) {
        List<Character> allowedLetters = new ArrayList<>();
        for (int i = 0; i < base - 10; i++) {
            allowedLetters.add(LETTERS.get(i));
        }
        for (int i = 0; i < digit.length(); i++) {
            char symbol = digit.charAt(i);
            if (!Character.isLetterOrDigit(symbol) ||
                    (Character.isDigit(symbol) && symbol - 48 > base - 1) ||
                    (Character.isLetter(symbol) && !allowedLetters.contains(symbol))) {
                throw new NumberFormatException();
            }
        }
    }

    private static BigInteger calcNumberInTensSystem(Number number) {
        if (number.getNumberSystem() == NumberSystemType._10) {
            return new BigInteger(number.getDigit());
        }
        int base = number.getNumberSystem().getNumberSystemIntValue();
        String digit = number.getDigit();
        int pow = digit.length() - 1;
        BigInteger result = BigInteger.ZERO;
        for (int i = 0; i < digit.length(); i++) {
            char symbol = digit.charAt(i);
            int value = (Character.isLetter(symbol)) ? LETTERS.indexOf(symbol) + 10 : symbol - 48;
            result = result.add(BigInteger.valueOf(value).multiply(calcPow(base, pow)));
            pow--;
        }
        return result;
    }

    private static BigInteger calcPow(int value, int pow) {
        BigInteger result = BigInteger.ONE;
        for (int i = 0; i < pow; i++) {
            result = result.multiply(BigInteger.valueOf(value));
        }
        return result;
    }

    private static Number numberInExpected(BigInteger value, NumberSystem expectedNumberSystem) {
        if (NumberSystemType._10.equals(expectedNumberSystem)) {
            return new Number(expectedNumberSystem, value.toString());
        }
        BigInteger base = BigInteger.valueOf(expectedNumberSystem.getNumberSystemIntValue());
        StringBuilder digit = new StringBuilder();
        while (value.compareTo(BigInteger.ZERO) > 0) {
            BigInteger mod = value.mod(base);
            digit.insert(0, modToString(mod));
            value = value.divide(base);
        }
        return new Number(expectedNumberSystem, digit.toString());
    }

    private static String modToString(BigInteger bigInteger) {
        int value = bigInteger.intValue();
        return (value < 10) ? String.valueOf(value) : String.valueOf(LETTERS.get(value - 10));
    }
}
