package com.javarush.task.pro.task15.task1523;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

/* 
Получение информации по API
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        URL url = new URL("http://httpbin.org/post");
        URLConnection connect = url.openConnection();
        connect.setDoOutput(true);
        try (OutputStream output = connect.getOutputStream();
             InputStream input = connect.getInputStream();
             PrintStream print = new PrintStream(output);
             BufferedReader reader = new BufferedReader(new InputStreamReader(input))) {
            print.println("Hello, httpbin.org/post.");
            while (reader.ready()) {
                System.out.println(reader.readLine());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

