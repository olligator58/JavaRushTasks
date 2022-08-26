package com.javarush.task.task25.task2512;

import java.util.LinkedList;
import java.util.List;

/* 
Живем своим умом
*/

public class Solution implements Thread.UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        List<Throwable> errors = new LinkedList<>();
        Throwable next = e;
        t.interrupt();
        while (next != null) {
            errors.add(0, next);
            next = next.getCause();
        }
        for (Throwable error : errors) {
            System.out.println(error.toString());
        }

    }


    public static void main(String[] args) {
        Solution s = new Solution();
        s.uncaughtException(Thread.currentThread(), new Exception("ABC", new RuntimeException("DEF", new IllegalAccessException("GHI"))));
    }
}
