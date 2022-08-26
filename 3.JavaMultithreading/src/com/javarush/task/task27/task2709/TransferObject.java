package com.javarush.task.task27.task2709;

public class TransferObject {
    private int value;
    protected volatile boolean isValuePresent = false;

    public synchronized int get() {
        try {
            while (!isValuePresent) {
                this.wait();
            }
        } catch (InterruptedException ignored) {
        }

        System.out.println("Got: " + value);
        isValuePresent = false;
        this.notifyAll();
        return value;
    }

    public synchronized void put(int value) {
        try {
            while (isValuePresent) {
                this.wait();
            }
        } catch (InterruptedException ignored) {
        }
        this.value = value;
        System.out.println("Put: " + value);
        isValuePresent = true;
        this.notifyAll();
    }
}
