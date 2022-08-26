package com.javarush.task.task20.task2016;

import java.io.*;

/* 
Минимум изменений
*/

public class Solution {
    public static class A implements Serializable {
        String name = "A";

        public A(String name) {
            this.name += name;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    public static class B extends A  {
        String name = "B";

        public B(String name) {
            super(name);
            this.name += name;
        }
    }

    public static class C extends B  {
        String name = "C";

        public C(String name) {
            super(name);
            this.name = name;
        }
    }

    public static void main(String[] args) throws Exception {
        C c = new C("X");
        ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("C:\\work\\JavaRush\\files\\C.ser"));
        output.writeObject(c);
        output.close();
        ObjectInputStream input = new ObjectInputStream(new FileInputStream("C:\\work\\JavaRush\\files\\C.ser"));
        C c1 = (C) input.readObject();
        System.out.println(c1.name);
    }
}
