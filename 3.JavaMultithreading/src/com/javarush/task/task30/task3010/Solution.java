package com.javarush.task.task30.task3010;

import java.util.regex.Pattern;

/* 
Минимальное допустимое основание системы счисления
*/

public class Solution {
    public static void main(String[] args) {
        try {
            if (args.length == 0) {
                return;
            }

            String s = args[0];
            Pattern p = Pattern.compile("[a-zA-Z0-9]+");
            if (!p.matcher(s).matches()) {
                System.out.println("incorrect");
                return;
            }

            s = s.toLowerCase();
            int maxChar = 0;
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) > maxChar) {
                    maxChar = s.charAt(i);
                }
            }

            int result = (maxChar >= 48 && maxChar <= 57) ? maxChar - 48 + 1 : maxChar - 87 + 1;
            System.out.println(Math.max(result, 2));
        } catch (Exception ignore) {
        }
    }
}