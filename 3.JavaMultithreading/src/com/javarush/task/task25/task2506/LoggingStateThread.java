package com.javarush.task.task25.task2506;

public class LoggingStateThread extends Thread {
    private Thread target;

    public LoggingStateThread(Thread target) {
        this.target = target;
        setDaemon(true);
    }

    @Override
    public void run() {
        State previous = null;
        State current;
        while (true) {
            if ((current = target.getState()) != previous) {
                System.out.println(current);
                previous = current;
            }
            if (current == State.TERMINATED) {
                stop();
            }
        }
    }
}
