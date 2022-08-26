package com.javarush.task.task26.task2603;

/*import java.util.ArrayList;
import java.util.Collections;*/
import java.util.Comparator;
//import java.util.List;

/* 
Убежденному убеждать других не трудно
*/

public class Solution {

    public static class CustomizedComparator <T> implements Comparator<T> {
        private Comparator<T>[] comparators;

        public CustomizedComparator(Comparator<T>...comparators) {
            this.comparators = comparators;
        }

        @Override
        public int compare(T o1, T o2) {
            int result = 0;
            for (int i = 0; i < comparators.length; i++) {
                result = comparators[i].compare(o1, o2);
                if (result != 0) {
                    break;
                }
            }
            return result;
        }
    }

    //This class is for testing
    /*public static class Person {
        private String name;
        private int age;
        private int height;

        public Person(String name, int age, int height) {
            this.name = name;
            this.age = age;
            this.height = height;
        }
    }*/

    public static void main(String[] args) {
        /*List<Person> persons = new ArrayList<>();
        persons.add(new Person("Anya", 28, 180));
        persons.add(new Person("Anya", 28, 170));
        persons.add(new Person("Anya", 23, 165));

        Comparator<Person> compName = (o1, o2) -> o1.name.compareTo(o2.name);
        Comparator<Person> compAgeDesc = (o1, o2) -> o2.age - o1.age;
        Comparator<Person> compHeight = (o1, o2) -> o1.height - o2.height;

        CustomizedComparator<Person> myComp = new CustomizedComparator<>(compName, compAgeDesc, compHeight);
        Collections.sort(persons, myComp);

        for (Person person : persons) {
            System.out.println(person.name + " " + person.age + " " + person.height);
        }*/
    }
}
