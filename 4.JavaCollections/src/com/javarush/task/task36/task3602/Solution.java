package com.javarush.task.task36.task3602;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;

/* 
Найти класс по описанию Ӏ Java Collections: 6 уровень, 6 лекция
*/

public class Solution {
    public static void main(String[] args) {
        System.out.println(getExpectedClass());
    }

    public static Class getExpectedClass() {
        Class<?>[] classes = Collections.class.getDeclaredClasses();
        for (Class<?> clazz : classes) {
            if (!List.class.isAssignableFrom(clazz)) {
                continue;
            }
            Method[] methods = clazz.getDeclaredMethods();
            Method getMethod = null;
            for (Method method : methods) {
                if (method.getName().equals("get")) {
                    getMethod = method;
                    break;
                }
            }
            if (getMethod == null) {
                continue;
            }
            try {
                Constructor<?> constructor;
                try {
                    constructor = clazz.getDeclaredConstructor();
                } catch (NoSuchMethodException | SecurityException e) {
                    continue;
                }
                constructor.setAccessible(true);
                Object list = constructor.newInstance();
                getMethod.setAccessible(true);
                try {
                    getMethod.invoke(list, 0);
                } catch (InvocationTargetException e) {
                    if (e.getCause().getClass() == IndexOutOfBoundsException.class) {
                        return clazz;
                    }
                }
            } catch (Exception ignored) {
            }
        }
        return null;
    }
}
