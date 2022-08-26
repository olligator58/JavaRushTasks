package com.javarush.task.task20.task2015;

import java.io.*;

/* 
Переопределение сериализации
*/

public class Solution implements Serializable, Runnable {
    private transient Thread runner;
    private int speed;

    public Solution(int speed) {
        this.speed = speed;
        runner = new Thread(this);
        runner.start();
    }

    public void run() {
        System.out.println(String.format("Скорость равна %d км/ч", speed));
    }

    /**
     * Переопределяем сериализацию.
     * Для этого необходимо объявить методы:
     * private void writeObject(ObjectOutputStream out) throws IOException
     * private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException
     * Теперь сериализация/десериализация пойдет по нашему сценарию :)
     */
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        Thread runner = new Thread(this);
        runner.start();
    }

    public static void main(String[] args) {
        Solution solution = new Solution(80);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (
             ObjectOutputStream output = new ObjectOutputStream(baos)) {
            output.writeObject(solution);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (ObjectInputStream input = new ObjectInputStream(new ByteArrayInputStream(baos.toByteArray()))) {
            input.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
