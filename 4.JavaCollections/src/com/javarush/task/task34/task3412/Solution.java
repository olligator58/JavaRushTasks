package com.javarush.task.task34.task3412;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/* 
Добавление логирования в класс
*/

public class Solution {
    private static final Logger logger = LoggerFactory.getLogger(Solution.class);

    private int value1;
    private String value2;
    private Date value3;

    public Solution(int value1, String value2, Date value3) {
        this.value1 = value1;
        this.value2 = value2;
        this.value3 = value3;
        logger.debug("Запущен конструктор " + value1 + value2 + value3);
    }

    public static void main(String[] args) {
        Solution s = new Solution(5, "Test", new Date());
        s.divide(20, 0);
        s.calculateAndSetValue3(1000);
        s.setValue1(356);
        s.setValue2("Новая строка");
        s.setValue3(new Date());
        s.printString();
        s.printDateAsLong();
    }

    public void calculateAndSetValue3(long value) {
        logger.trace("Запущена calculateAndSetValue3. Старое значение поля value1: " + value1);
        value -= 133;
        if (value > Integer.MAX_VALUE) {
            value1 = (int) (value / Integer.MAX_VALUE);
            logger.debug("Новое значение поля value1: " + value1);
        } else {
            value1 = (int) value;
            logger.debug("Новое значение поля value1: " + value1);
        }
    }

    public void printString() {
        logger.trace("Запущена printString. value2 = " + value2);
        if (value2 != null) {
            System.out.println(value2.length());
        }
    }

    public void printDateAsLong() {
        logger.trace("Запущена printDateAsLong. value3 = " + value3);
        if (value3 != null) {
            System.out.println(value3.getTime());
        }
    }

    public void divide(int number1, int number2) {
        logger.trace("Запущен divide");
        try {
            System.out.println(number1 / number2);
        } catch (ArithmeticException e) {
            logger.error("Возникла ошибка при делении " + number1 + " на " + number2, e);
        }
    }

    public void setValue1(int value1) {
        logger.debug("Значение поля value1 изменено с " + this.value1 + " на " + value1);
        this.value1 = value1;
    }

    public void setValue2(String value2) {
        logger.debug("Значение поля value2 изменено с " + this.value2 + " на " + value2);
        this.value2 = value2;
    }

    public void setValue3(Date value3) {
        logger.debug("Значение поля value3 изменено с " + this.value3 + " на " + value3);
        this.value3 = value3;
    }
}
