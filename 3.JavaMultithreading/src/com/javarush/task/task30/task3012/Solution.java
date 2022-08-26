package com.javarush.task.task30.task3012;

/* 
Получи заданное число
*/

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.createExpression(1234);
    }

    public void createExpression(int number) {
        String numInTripple = calcNumInTripple(number);
        printTripple(numInTripple, number);
    }

    private void printTripple(String number, int value) {
        StringBuilder line = new StringBuilder(String.format("%d = ", value));
        number = new StringBuilder(number).reverse().toString();
        for (int i = 0; i < number.length(); i++) {
            char symbol = number.charAt(i);
            if (symbol != '0') {
                line.append(String.format("%s %d", symbol, (int) Math.pow(3, i)));
                line.append(" ");
            }
        }
        System.out.println(line.toString().trim());
    }

    private String calcNumInTripple(int number) {
        StringBuilder result = new StringBuilder();
        while (number > 0) {
            int mod = number % 3;
            number = number / 3;
            switch (mod) {
                case 0:
                    result.insert(0, "0");
                    break;
                case 1:
                    result.insert(0, "+");
                    break;
                case 2:
                    result.insert(0, "-");
                    number++;
                    break;
            }
        }
        return result.toString();
    }
}