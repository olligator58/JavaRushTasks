package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Dish;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ConsoleHelper {
    private static final BufferedReader console = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() throws IOException {
        return console.readLine();
    }

    public static List<Dish> getAllDishesForOrder() throws IOException {
        List<Dish> result = new ArrayList<>();
        writeMessage("Список доступных блюд:");
        writeMessage(Dish.allDishesToString());
        writeMessage("Введите название блюда:");
        while (true) {
            String answer = readString();
            if (answer.equalsIgnoreCase("exit")) {
                break;
            }
            boolean dishInList = false;
            for (Dish dish : Dish.values()) {
                if (dish.toString().equalsIgnoreCase(answer)) {
                    result.add(dish);
                    dishInList = true;
                    writeMessage("Блюдо " + dish.toString() + " принято");
                    break;
                }
            }
            if (!dishInList) {
                writeMessage("Такого блюда нет в списке.");
            }
        }
        return result;
    }
}
