package com.javarush.task.task26.task2601;

import java.util.Arrays;
import java.util.Comparator;

/* 
Почитать в инете про медиану выборки
*/

public class Solution {

    public static void main(String[] args) {
        /*Integer[] a = new Integer[] {13, 8, 15, 5, 17};
        System.out.println("Исходный массив:");
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
        System.out.println("Медиана: " + getMediana(a));
        System.out.println("После сортировки: ");
        sort(a);
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }*/
    }

    public static Integer[] sort(Integer[] array) {
        if (array != null && array.length > 0) {
            int mediana = getMediana(array);
            Comparator<Integer> comparator = new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    int result;
                    int o1Distance = Math.abs(mediana - o1);
                    int o2Distance = Math.abs(mediana - o2);
                    if (o1Distance != o2Distance) {
                        result = o1Distance - o2Distance;
                    } else {
                        result = o1 - o2;
                    }
                    return result;
                }
            };
            Arrays.sort(array, comparator);
        }
        return array;
    }

    private static int getMediana(Integer[] array) {
        int result;
        Integer[] copy = array.clone();
        int middle = copy.length / 2;
        Arrays.sort(copy);
        if (copy.length % 2 != 0) {
            result = copy[middle];
        } else {
            result = (copy[middle] + copy[middle - 1]) / 2;
        }
        return result;
    }
}
