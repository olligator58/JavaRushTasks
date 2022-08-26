package com.javarush.task.task20.task2014;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/* 
Serializable Solution
*/

public class Solution implements Serializable {
    public static void main(String[] args) {
        System.out.println(new Solution(4));

        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("C:\\work\\JavaRush\\files\\Solution.bin"));
             ObjectInputStream input = new ObjectInputStream(new FileInputStream("C:\\work\\JavaRush\\files\\Solution.bin"))) {
            Solution savedObject = new Solution(18);
            output.writeObject(savedObject);
            output.flush();
            Solution loadedObject = new Solution(25);
            loadedObject = (Solution) input.readObject();
            System.out.println(loadedObject.toString().equals(savedObject.toString()));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private transient final String pattern = "dd MMMM yyyy, EEEE";
    private transient Date currentDate;
    private transient int temperature;
    String string;

    public Solution(int temperature) {
        this.currentDate = new Date();
        this.temperature = temperature;

        string = "Today is %s, and the current temperature is %s C";
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        this.string = String.format(string, format.format(currentDate), temperature);
    }

    @Override
    public String toString() {
        return this.string;
    }
}
