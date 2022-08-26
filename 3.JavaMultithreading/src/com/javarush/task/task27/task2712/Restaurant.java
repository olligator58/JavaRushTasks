package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Cook;
import com.javarush.task.task27.task2712.kitchen.Order;
import com.javarush.task.task27.task2712.kitchen.Waiter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

public class Restaurant {
    private static final int ORDER_CREATING_INTERVAL = 100;
    private static final LinkedBlockingQueue<Order> ORDER_QUEUE = new LinkedBlockingQueue<>();

    public static void main(String[] args) {
        Cook cook1 = new Cook("Geronimo");
        cook1.setQueue(ORDER_QUEUE);
        Cook cook2 = new Cook("Idalgo");
        cook2.setQueue(ORDER_QUEUE);

        List<Tablet> tablets = new ArrayList<>();

        for (int i = 1; i <= 5; i++) {
            Tablet tablet = new Tablet(i);
            tablet.setQueue(ORDER_QUEUE);
            tablets.add(tablet);
        }


        Waiter waiter = new Waiter();
        cook1.addObserver(waiter);
        cook2.addObserver(waiter);

        Thread cookThread1 = new Thread(cook1);
        cookThread1.setDaemon(true);
        Thread cookThread2 = new Thread(cook2);
        cookThread2.setDaemon(true);
        cookThread1.start();
        cookThread2.start();

        Thread threadTaskGenerator = new Thread(new RandomOrderGeneratorTask(tablets, ORDER_CREATING_INTERVAL));
        threadTaskGenerator.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ignored) {
        }
        threadTaskGenerator.interrupt();
        try {
            Thread.sleep(100);
        } catch (InterruptedException ignored) {
        }

        DirectorTablet directorTablet = new DirectorTablet();
        directorTablet.printAdvertisementProfit();
        directorTablet.printCookWorkloading();
        directorTablet.printActiveVideoSet();
        directorTablet.printArchivedVideoSet();
    }
}
