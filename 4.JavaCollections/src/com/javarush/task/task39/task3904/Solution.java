package com.javarush.task.task39.task3904;

/*
Лестница
*/

public class Solution {
    private static int n = 70;

    public static void main(String[] args) {
        System.out.println("The number of possible ascents for " + n + " steps is: " + numberOfPossibleAscents(n));
    }

    public static long numberOfPossibleAscents(int n) {
        if (n < 0) {
            return 0L;
        } else if (n == 0 || n == 1) {
            return 1L;
        } else if (n == 2) {
            return 2L;
        }

        long[] steps = new long[n + 1];
        steps[0] = steps[1] = 1L;
        steps[2] = 2L;
        for (int i = 3; i <= n; i++) {
            steps[i] = steps[i - 3] + steps[i - 2] + steps[i - 1];
        }
        return steps[n];
    }
}

