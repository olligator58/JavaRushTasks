package com.javarush.task.task27.task2712.kitchen;

import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.Tablet;

import java.io.IOException;
import java.util.List;

public class Order {
    private final Tablet tablet;
    protected List<Dish> dishes;

    public Order(Tablet tablet) throws IOException {
        this.tablet = tablet;
        initDishes();
    }

    protected void initDishes() throws IOException {
        dishes = ConsoleHelper.getAllDishesForOrder();
    }

    public Tablet getTablet() {
        return tablet;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public int getTotalCookingTime() {
        int result = 0;
        for (Dish dish : dishes) {
            result += dish.getDuration();
        }
        return result;
    }

    public boolean isEmpty() {
        return dishes.isEmpty();
    }

    @Override
    public String toString() {
        String result;
        if (dishes.isEmpty()) {
            result = "";
        } else {
            StringBuilder dishesList = new StringBuilder();
            for (Dish dish : dishes) {
                dishesList.append(dishesList.toString().isEmpty() ? "" : ", ");
                dishesList.append(dish.toString());
            }
            result = String.format("Your order: [%s] of %s, cooking time %dmin", dishesList.toString(), tablet, getTotalCookingTime());
        }
        return result;
    }
}
