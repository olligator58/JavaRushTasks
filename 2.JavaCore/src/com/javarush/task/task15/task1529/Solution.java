package com.javarush.task.task15.task1529;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* 
Осваивание статического блока
*/

public class Solution {
    public static void main(String[] args) {

    }

    static {
        reset();
    }

    public static CanFly result;

    public static void reset() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            String param = reader.readLine();
            if ("helicopter".equals(param)) {
                result = new Helicopter();
            } else if ("plane".equals(param)) {
                int number = Integer.parseInt(reader.readLine());
                result = new Plane(number);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
