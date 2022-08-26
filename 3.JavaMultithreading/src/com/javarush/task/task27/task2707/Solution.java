package com.javarush.task.task27.task2707;

/* 
Определяем порядок захвата монитора
*/

public class Solution {
    public void someMethodWithSynchronizedBlocks(Object obj1, Object obj2) {
        synchronized (obj1) {
            synchronized (obj2) {
                System.out.println(obj1 + " " + obj2);
            }
        }
    }

    public static boolean isLockOrderNormal(final Solution solution, final Object o1, final Object o2) throws Exception {
        boolean result = false;
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                solution.someMethodWithSynchronizedBlocks(o1, o2);
            }
        };
        Runnable r2 = new Runnable() {
            @Override
            public void run() {
                synchronized (o2) {
                }
            }
        };
        synchronized (o1) {
            Thread t1 = new Thread(r1);
            Thread t2 = new Thread(r2);
            t1.start();
            Thread.sleep(100);
            t2.start();
            Thread.sleep(300);
            if (t2.getState() != Thread.State.BLOCKED) {
                result = true;
            }
        }

        return result;
    }

    public static void main(String[] args) throws Exception {
        final Solution solution = new Solution();
        final Object o1 = new Object();
        final Object o2 = new Object();

        System.out.println(isLockOrderNormal(solution, o1, o2));
    }
}
