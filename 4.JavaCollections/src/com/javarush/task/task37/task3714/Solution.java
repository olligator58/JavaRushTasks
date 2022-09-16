package com.javarush.task.task37.task3714;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/* 
Древний Рим
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Input a roman number to be converted to decimal: ");
        String romanString = bufferedReader.readLine();
        System.out.println("Conversion result equals " + romanToInteger(romanString));
    }

    public static int romanToInteger(String s) {
        Map<Character, Integer> numbers = new HashMap<>();
        numbers.put('I', 1);
        numbers.put('V', 5);
        numbers.put('X', 10);
        numbers.put('L', 50);
        numbers.put('C', 100);
        numbers.put('D', 500);
        numbers.put('M', 1000);

        int result = 0;
        Integer currentNumber;
        int previousNumber = Integer.MAX_VALUE;

        for (int i = 0; i < s.length(); i++) {
            char c = s.toUpperCase().charAt(i);
            if ((currentNumber = numbers.get(c)) == null) {
                continue;
            }
            int delta = (currentNumber <= previousNumber) ? currentNumber : currentNumber - previousNumber * 2;
            result += delta;
            previousNumber = currentNumber;
        }
        return result;
    }
}
