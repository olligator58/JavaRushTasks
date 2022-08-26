package com.javarush.task.task28.task2802;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/* 
Пишем свою ThreadFactory
*/

public class Solution {

    public static class AmigoThreadFactory implements ThreadFactory {
        private static volatile AtomicInteger counter = new AtomicInteger(0);
        private final AtomicInteger a;
        private AtomicInteger b = new AtomicInteger(0);

        public AmigoThreadFactory() {
            a = new AtomicInteger(counter.incrementAndGet());
        }

        @Override
        public Thread newThread(Runnable r) {
            ThreadGroup threadGroup = Thread.currentThread().getThreadGroup();
            Thread thread = new Thread(threadGroup, r, String.format("%s-pool-%s-thread-%s", threadGroup.getName(), a.get(), b.incrementAndGet()));
            thread.setDaemon(false);
            thread.setPriority(Thread.NORM_PRIORITY);
            return thread;
        }
    }

    public static void main(String[] args) {
        class EmulatorThreadFactoryTask implements Runnable {
            @Override
            public void run() {
                emulateThreadFactory();
            }
        }

        ThreadGroup group = new ThreadGroup("firstGroup");
        Thread thread = new Thread(group, new EmulatorThreadFactoryTask());

        ThreadGroup group2 = new ThreadGroup("secondGroup");
        Thread thread2 = new Thread(group2, new EmulatorThreadFactoryTask());

        thread.start();
        thread2.start();
    }

    private static void emulateThreadFactory() {
        AmigoThreadFactory factory = new AmigoThreadFactory();
        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        };
        factory.newThread(r).start();
        factory.newThread(r).start();
        factory.newThread(r).start();
    }
}
