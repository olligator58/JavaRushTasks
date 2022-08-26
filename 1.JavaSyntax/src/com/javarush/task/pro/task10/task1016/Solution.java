package com.javarush.task.pro.task10.task1016;

/* 
Прогноз погоды
*/

public class Solution {

    public static void showWeather(City city) {
        String text = "В городе %s сегодня температура воздуха %d";
        System.out.println(String.format(text,city.getName(), city.getTemperature()));
    }

    public static void main(String[] args) {
        showWeather(new City("Дубай", 40));
    }
}
