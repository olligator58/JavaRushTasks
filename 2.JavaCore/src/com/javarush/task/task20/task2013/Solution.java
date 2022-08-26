package com.javarush.task.task20.task2013;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/* 
Externalizable Person
*/

public class Solution {
    public static class Person implements Externalizable {
        private String firstName;
        private String lastName;
        private int age;
        private Person mother;
        private Person father;
        private List<Person> children;

        public Person() {

        }

        public Person(String firstName, String lastName, int age) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.age = age;
        }

        public void setMother(Person mother) {
            this.mother = mother;
        }

        public void setFather(Person father) {
            this.father = father;
        }

        public void setChildren(List<Person> children) {
            this.children = children;
        }

        @Override
        public void writeExternal(ObjectOutput out) throws IOException {
            out.writeObject(firstName);
            out.writeObject(lastName);
            out.writeObject(father);
            out.writeObject(mother);
            out.writeInt(age);
            out.writeObject(children);
        }

        @Override
        public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
            firstName = (String) in.readObject();
            lastName = (String) in.readObject();
            father = (Person) in.readObject();
            mother = (Person) in.readObject();
            age = in.readInt();
            children = (List) in.readObject();
        }
    }

    public static void main(String[] args) {
        Person ivanov = new Person("Ваня", "Иванов", 30);
        ivanov.setFather(new Person("Батя", "Иванов", 60));
        ivanov.setMother(new Person("Люба", "Иванова", 55));
        List<Person> kids = new ArrayList<>();
        kids.add(new Person("Матвей", "Иванов", 10));
        kids.add(new Person("Лидочка", "Иванова", 5));
        ivanov.setChildren(kids);

        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("C:\\work\\JavaRush\\files\\Person.bin"))) {
            ivanov.writeExternal(output);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Person iv1 = new Person();
        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream("C:\\work\\JavaRush\\files\\Person.bin"));) {
            iv1.readExternal(input);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println(iv1.firstName + " " + iv1.lastName + " " + iv1.age);
        System.out.println(iv1.father.firstName + " " + iv1.father.lastName + " " + iv1.father.age);
        System.out.println(iv1.mother.firstName + " " + iv1.mother.lastName + " " + iv1.mother.age);
        for (Person child : iv1.children) {
            System.out.println(child.firstName + " " + child.lastName + " " + child.age);
        }
    }
}
