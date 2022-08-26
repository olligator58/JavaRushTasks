package com.javarush.task.task20.task2020;

import java.io.*;
import java.util.logging.Logger;

/* 
Сериализация человека
*/

public class Solution {

    public static class Person implements Serializable {
        String firstName;
        String lastName;
        transient String fullName;
        transient final String greeting;
        String country;
        Sex sex;
        transient PrintStream outputStream;
        transient Logger logger;

        Person(String firstName, String lastName, String country, Sex sex) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.fullName = String.format("%s, %s", lastName, firstName);
            this.greeting = "Hello, ";
            this.country = country;
            this.sex = sex;
            this.outputStream = System.out;
            this.logger = Logger.getLogger(String.valueOf(Person.class));
        }
    }

    enum Sex {
        MALE,
        FEMALE
    }

    public static void main(String[] args) {
        Person ivanov = new Person("Вася", "Хренов", "Лапландия", Sex.MALE);
        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("C:\\work\\JavaRush\\files\\Person.ser"))) {
            output.writeObject(ivanov);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream("C:\\work\\JavaRush\\files\\Person.ser"))) {
            Person i1 = (Person) input.readObject();
            System.out.println(i1.firstName);
            System.out.println(i1.lastName);
            System.out.println(i1.fullName);
            System.out.println(i1.greeting);
            System.out.println(i1.country);
            System.out.println(i1.sex);
            System.out.println(i1.outputStream);
            System.out.println(i1.logger);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
