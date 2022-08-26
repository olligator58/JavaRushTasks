package com.javarush.task.task28.task2805;

import java.util.concurrent.atomic.AtomicInteger;

public class MyThread extends Thread {
    private static volatile AtomicInteger counter = new AtomicInteger(Thread.MIN_PRIORITY);

    public MyThread() {
        super();
        setThreadPriority();
    }

    public MyThread(Runnable target) {
        super(target);
        setThreadPriority();
    }

    public MyThread(ThreadGroup group, Runnable target) {
        super(group, target);
        setThreadPriority();
    }

    public MyThread(String name) {
        super(name);
        setThreadPriority();
    }

    public MyThread(ThreadGroup group, String name) {
        super(group, name);
        setThreadPriority();
    }

    public MyThread(Runnable target, String name) {
        super(target, name);
        setThreadPriority();
    }

    public MyThread(ThreadGroup group, Runnable target, String name) {
        super(group, target, name);
        setThreadPriority();
    }

    public MyThread(ThreadGroup group, Runnable target, String name, long stackSize) {
        super(group, target, name, stackSize);
        setThreadPriority();
    }

    private void setThreadPriority() {
        if (counter.get() > Thread.MAX_PRIORITY) {
            counter.set(Thread.MIN_PRIORITY);
        }
        int priority = counter.getAndIncrement();
        ThreadGroup threadGroup = this.getThreadGroup();
        if (threadGroup != null && priority > threadGroup.getMaxPriority()) {
            priority = threadGroup.getMaxPriority();
        }
        this.setPriority(priority);
    }
}
