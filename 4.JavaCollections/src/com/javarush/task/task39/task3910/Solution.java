package com.javarush.task.task39.task3910;

/* 
isPowerOfThree
*/

public class Solution {
    public static void main(String[] args) {
        System.out.println(isPowerOfThree(59049));
    }

    public static boolean isPowerOfThree(int n) {
        if (n <= 0) {
            return false;
        }
        return 1162261467 % n == 0;
    }
}
