package com.javarush.task.task39.task3908;

/* 
Возможен ли палиндром?
*/

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static void main(String[] args) {
        System.out.println(isPalindromePermutation("zakka"));
    }

    public static boolean isPalindromePermutation(String s) {
        List<String> words = new ArrayList<>();
        permute(s.toLowerCase().toCharArray(), 0, s.length() - 1, words);
        return !words.isEmpty();
    }

    private static void permute(char[] letters, int l, int r, List<String> result) {
        if (!result.isEmpty()) {
            return;
        }

        if (l == r && isPalindrome(letters)) {
            result.add(new String(letters));
            return;
        }

        for (int i = l; i <= r ; i++) {
            swap(letters, l, i);
            permute(letters, l + 1, r, result);
            swap(letters, l, i);
        }
    }

    private static void swap(char[] letters, int i, int j) {
        char temp = letters[i];
        letters[i] = letters[j];
        letters[j] = temp;
    }

    private static boolean isPalindrome(char[] letters) {
        String original = new String(letters);
        StringBuilder sb = new StringBuilder(original).reverse();
        return original.equals(sb.toString());
    }
}
