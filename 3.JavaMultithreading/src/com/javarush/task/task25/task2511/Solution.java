package com.javarush.task.task25.task2511;

import java.util.TimerTask;

/* 
Вооружаемся до зубов!
*/

public class Solution extends TimerTask {
    protected TimerTask original;
    protected final Thread.UncaughtExceptionHandler handler;

    public Solution(TimerTask original) {
        if (original == null) {
            throw new NullPointerException();
        }
        this.original = original;
        this.handler = new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                StringBuilder sb = new StringBuilder();
                String[] words = e.getMessage().split("\\s");
                for (int i = 0; i < words.length; i++) {
                    if (words[i].indexOf("Thread") >= 0) {
                        words[i] = words[i].replaceAll(".", "*");
                    }
                    sb.append(words[i]);
                    sb.append(" ");
                }
                System.out.println(sb.toString().trim());
            }
        };
    }

    @Override
    public void run() {
        try {
            original.run();
        } catch (Throwable cause) {
            Thread currentThread = Thread.currentThread();
            handler.uncaughtException(currentThread, new Exception("Blah " + currentThread.getName() + " blah-blah-blah", cause));
        }
    }

    @Override
    public long scheduledExecutionTime() {
        return original.scheduledExecutionTime();
    }

    @Override
    public boolean cancel() {
        return original.cancel();
    }

    public static void main(String[] args) {
        Solution s = new Solution(new TimerTask() {
            @Override
            public void run() {
                throw new RuntimeException();
            }
        });
        Thread t = new Thread(s);
        t.start();

    }
}
