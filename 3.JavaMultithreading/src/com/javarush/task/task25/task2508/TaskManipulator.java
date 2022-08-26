package com.javarush.task.task25.task2508;

public class TaskManipulator implements Runnable, CustomThreadManipulator {
    Thread thread;

    @Override
    public void run() {
        Thread current = Thread.currentThread();
        while (!current.isInterrupted()) {
            System.out.println(current.getName());
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                break;
            }
        }
    }

    @Override
    public void start(String threadName) {
        Thread t = new Thread(new TaskManipulator(), threadName);
        thread = t;
        thread.start();
    }

    @Override
    public void stop() {
        thread.interrupt();
    }
}
