package com.javarush.task.task20.task2003;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/* 
Знакомство с properties
*/

public class Solution {

    public static Map<String, String> runtimeStorage = new HashMap<>();

    public static void save(OutputStream outputStream) throws Exception {
        Properties properties = new Properties();
        for (Map.Entry<String, String> pair : runtimeStorage.entrySet()) {
            properties.setProperty(pair.getKey(), pair.getValue());
        }
        properties.store(outputStream, "");
    }

    public static void load(InputStream inputStream) throws IOException {
        Properties properties = new Properties();
        properties.load(inputStream);
        runtimeStorage.clear();
        for (Map.Entry<Object, Object> property : properties.entrySet()) {
            String key = (String) property.getKey();
            String value = (String) property.getValue();
            runtimeStorage.put(key, value);
        }
    }

    public static void main(String[] args) {

        try (BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
             FileInputStream input = new FileInputStream(console.readLine());
             FileOutputStream output = new FileOutputStream(console.readLine())) {
            load(input);
            save(output);

        } catch (Exception e) {
            e.printStackTrace();
        }


        System.out.println(runtimeStorage);
    }
}
