package com.javarush.task.task21.task2109;

/* 
Запретить клонирование
*/

public class Solution {
    public static class A implements Cloneable {
        private int i;
        private int j;

        public A(int i, int j) {
            this.i = i;
            this.j = j;
        }

        public int getI() {
            return i;
        }

        public int getJ() {
            return j;
        }

        @Override
        public Object clone() throws CloneNotSupportedException {
            return super.clone();
        }
    }

    public static class B extends A {
        private String name;

        public B(int i, int j, String name) {
            super(i, j);
            this.name = name;
        }

        public String getName() {
            return name;
        }

        @Override
        public Object clone() throws CloneNotSupportedException {
            throw new CloneNotSupportedException();
        }
    }

    public static class C extends B {
        public C(int i, int j, String name) {
            super(i, j, name);
        }

        @Override
        public Object clone() throws CloneNotSupportedException {
            return new C(getI(), getJ(), getName());
        }
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        A a = new A(3, 5);
        A a1 = (A) a.clone();
        System.out.println(a);
        System.out.println(a1);
        System.out.println(a1.getI() + " " + a1.getJ());

        B b = new B(6, 10, "This is B");
        try {
            B b1 = (B) b.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        C c = new C(12, 23, "This is C");
        C c1 = (C) c.clone();
        System.out.println(c);
        System.out.println(c1);
        System.out.println(c1.getName() + " " + c1.getI() + " " + c1.getJ());
    }
}
