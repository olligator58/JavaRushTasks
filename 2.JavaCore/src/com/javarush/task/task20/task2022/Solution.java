package com.javarush.task.task20.task2022;

import java.io.*;

/* 
Переопределение сериализации в потоке
*/

public class Solution implements Serializable, AutoCloseable {
    private transient FileOutputStream stream;
    private String fileName;

    public Solution(String fileName) throws FileNotFoundException {
        this.stream = new FileOutputStream(fileName);
        this.fileName = fileName;
    }

    public void writeObject(String string) throws IOException {
        stream.write(string.getBytes());
        stream.write("\n".getBytes());
        stream.flush();
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        stream = new FileOutputStream(fileName, true);
    }

    @Override
    public void close() throws Exception {
        System.out.println("Closing everything!");
        stream.close();
    }

    public static void main(String[] args)  {
        String serFileName = "C:\\work\\JavaRush\\files\\Solution.ser";
        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(serFileName))) {
            Solution solution = new Solution("C:\\work\\JavaRush\\files\\Solution.txt");
            solution.writeObject("Это просто текст");
            output.writeObject(solution);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(serFileName));) {
            Solution s1 = (Solution) input.readObject();
            s1.writeObject("А вот еще текст");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
