package com.javarush.task.task39.task3909;

/* 
Одно изменение
*/

public class Solution {
    public static void main(String[] args) {
        System.out.println(isOneEditAway("whui", "huy"));
    }

    public static boolean isOneEditAway(String first, String second) {
        if (Math.abs(first.length() - second.length()) > 1) {
            return false;
        }

        StringBuilder s1;
        StringBuilder s2;
        if (first.length() <= second.length()) {
            s1 = new StringBuilder(first.toLowerCase());
            s2 = new StringBuilder(second.toLowerCase());
        } else {
            s2 = new StringBuilder(first.toLowerCase());
            s1 = new StringBuilder(second.toLowerCase());
        }

        int diffCounter = 0;
        if (s1.length() == s2.length()) {
            for (int i = 0; i < s1.length(); i++) {
                if (s1.charAt(i) != s2.charAt(i)) {
                    diffCounter++;
                }
            }
            return diffCounter <= 1;
        }

        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                if (diffCounter > 0) {
                    return false;
                }
                diffCounter++;
                s2.deleteCharAt(i);
                i--;
            }
        }
        return diffCounter <= 1;
    }
}
