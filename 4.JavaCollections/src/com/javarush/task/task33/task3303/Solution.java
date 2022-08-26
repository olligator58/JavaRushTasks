package com.javarush.task.task33.task3303;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/* 
Десериализация JSON объекта
*/

public class Solution {
    public static <T> T convertFromJsonToNormal(String fileName, Class<T> clazz) throws IOException {
        FileReader reader = new FileReader(fileName);
        ObjectMapper mapper = new ObjectMapper();

        return mapper.readValue(reader, clazz);
    }

    public static void main(String[] args) {
        ObjectMapper mapper = new ObjectMapper();
        try (FileWriter writer = new FileWriter("C:/work/JavaRush/files/Cat.json")) {
            Cat cat = new Cat();
            cat.name = "Barsik";
            cat.age = 4;
            cat.owner = "Master";
            mapper.writeValue(writer, cat);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Cat cat = convertFromJsonToNormal("C:/work/JavaRush/files/Cat.json", Cat.class);
            System.out.println(cat);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //debug
    @JsonAutoDetect
    public static class Cat {
        public String name;
        public int age;
        public String owner;

        @Override
        public String toString() {
            return "Cat{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    ", holder='" + owner + '\'' +
                    '}';
        }
    }



}
