package com.javarush.task.task38.task3803;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/* 
Runtime исключения (unchecked exception)
*/

public class VeryComplexClass {
    public void methodThrowsClassCastException() {
        List<String> list = new ArrayList<>();
        List<String> list2 = (LinkedList) list;
    }

    public void methodThrowsNullPointerException() {
        List<String> list = null;
        list.add("1");
    }

    public static void main(String[] args) {
        VeryComplexClass vcc = new VeryComplexClass();
//        vcc.methodThrowsClassCastException();
        vcc.methodThrowsNullPointerException();
    }
}
