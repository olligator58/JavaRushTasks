package com.javarush.task.task19.task1914;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/* 
Решаем пример
*/

public class Solution {
    public static TestString testString = new TestString();

    public static void main(String[] args) {
        PrintStream console = System.out;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream output = new PrintStream(byteArrayOutputStream);
        System.setOut(output);
        testString.printSomething();
        System.setOut(console);
        String[] numbers = byteArrayOutputStream.toString().split("\\s");

        int a = Integer.parseInt(numbers[0]);
        int b = Integer.parseInt(numbers[2]);
        String operation = numbers[1];

        int c = 0;
        switch (operation) {
            case "+":
                c = a + b;
                break;
            case "-":
                c = a - b;
                break;
            case "*":
                c = a * b;
                break;
            default:
        }
        System.out.println(String.format("%d %s %d = %d", a, operation, b, c));
    }

    public static class TestString {
        public void printSomething() {
            System.out.println("3 + 6 = ");
        }
    }
}

