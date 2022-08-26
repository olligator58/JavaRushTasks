package com.javarush.task.task15.task1527;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* 
Парсер реквестов
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String url = reader.readLine();
        String params = "";
        String objValue = null;
        if (url.indexOf('?') >= 0) {
            params = url.substring(url.indexOf('?') + 1);
            StringTokenizer st = new StringTokenizer(params, "&");
            while (st.hasMoreTokens()) {
                String token = st.nextToken();
                String[] pair = token.split("=");
                System.out.print(pair[0] + " ");
                if (pair[0].equals("obj") && pair.length == 2) {
                    objValue = pair[1];
                }
            }
            if (objValue != null) {
                System.out.println();
                try {
                    double value = Double.parseDouble(objValue);
                    alert(value);
                } catch (NumberFormatException e) {
                    alert(objValue);
                }
            }
        }
    }

    public static void alert(double value) {
        System.out.println("double: " + value);
    }

    public static void alert(String value) {
        System.out.println("String: " + value);
    }
}
