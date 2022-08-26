package com.javarush.task.task27.task2712.kitchen;

import com.javarush.task.task27.task2712.Tablet;

import java.io.IOException;
import java.util.ArrayList;

public class TestOrder extends Order {

    public TestOrder(Tablet tablet) throws IOException {
        super(tablet);
    }

    @Override
    protected void initDishes() throws IOException {
        dishes = new ArrayList<>();
        int numOfDishes = (int) (Math.random() * Dish.values().length) + 1;
        for (int i = 0; i < numOfDishes; i++) {
            int randomIndex = (int) (Math.random() * Dish.values().length);
            dishes.add(Dish.values()[randomIndex]);
        }
    }
}
