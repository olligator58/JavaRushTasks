package com.javarush.task.task22.task2208;

import java.util.LinkedHashMap;
import java.util.Map;

/* 
Формируем WHERE
*/

public class Solution {
    public static void main(String[] args) {
        Map<String, String> params = new LinkedHashMap<>();
        params.put("name", "Ivanov");
        params.put("country", "Ukraine");
        params.put("city", "Kiev");
        params.put("age", null);
        System.out.println(getQuery(params));
    }

    public static String getQuery(Map<String, String> params) {
        StringBuilder result = new StringBuilder("");
        int i = 0;
        for (Map.Entry<String, String> param : params.entrySet()) {
            if (param.getValue() != null) {
                if (i > 0) {
                    result.append(" and ");
                }
                result.append(String.format("%s = '%s'", param.getKey(), param.getValue()));
                i++;
            }
        }

        return result.toString();
    }
}
