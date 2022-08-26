package com.javarush.task.pro.task13.task1309;

import java.util.HashMap;

/* 
Успеваемость студентов
*/

public class Solution {
    public static HashMap<String, Double> grades = new HashMap<>();

    public static void main(String[] args) {
        addStudents();
        System.out.println(grades);
    }

    public static void addStudents() {
        grades.put("Коля", 5D);
        grades.put("Вася", 4.5);
        grades.put("Петя", 4D);
        grades.put("Андрюша", 3.5);
        grades.put("Слава", 3.0);
    }
}
