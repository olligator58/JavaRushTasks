package com.javarush.task.task20.task2021;

import java.io.*;

/* 
Сериализация под запретом
*/

public class Solution implements Serializable {

    public static class SubSolution extends Solution {

        private void writeObject(ObjectOutputStream output) throws IOException {
            throw new NotSerializableException();
        }

        private void readObject(ObjectInputStream input) throws IOException, ClassNotFoundException {
            throw new NotSerializableException();
        }
    }

    public static void main(String[] args) {
        SubSolution subSolution = new SubSolution();

        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("C:\\work\\JavaRush\\files\\SubSolution.ser"))) {
            output.writeObject(subSolution);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream("C:\\work\\JavaRush\\files\\SubSolution.ser"));) {
            SubSolution sub1 = (SubSolution) input.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
