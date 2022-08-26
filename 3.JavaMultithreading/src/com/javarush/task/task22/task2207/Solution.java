package com.javarush.task.task22.task2207;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/* 
Обращенные слова
*/

public class Solution {
    public static List<Pair> result = new LinkedList<>();

    public static void main(String[] args) {
        String fileName = "";
        try (BufferedReader console = new BufferedReader(new InputStreamReader(System.in));) {
            fileName = console.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<String> words = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            while (reader.ready()) {
                String[] line = reader.readLine().split("\\s");
                for (int i = 0; i < line.length; i++) {
                    words.add(line[i]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < words.size() - 1; i++) {
            boolean found = false;
            for (int j = i + 1; j < words.size(); j++) {
                StringBuilder reverse = new StringBuilder(words.get(j)).reverse();
                if (words.get(i).equals(reverse.toString())) {
                    Pair pair = new Pair();
                    pair.first = words.get(i);
                    pair.second = reverse.reverse().toString();
                    result.add(pair);
                    words.remove(j);
                    words.remove(i);
                    found = true;
                    break;
                }
            }
            if (found) {
                i--;
            }
        }

        for (Pair pair : result) {
            System.out.println(pair);
        }

    }

    public static class Pair {
        String first;
        String second;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Pair pair = (Pair) o;

            if (first != null ? !first.equals(pair.first) : pair.first != null) return false;
            return second != null ? second.equals(pair.second) : pair.second == null;

        }

        @Override
        public int hashCode() {
            int result = first != null ? first.hashCode() : 0;
            result = 31 * result + (second != null ? second.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return first == null && second == null ? "" :
                    first == null ? second :
                            second == null ? first :
                                    first.compareTo(second) < 0 ? first + " " + second : second + " " + first;

        }
    }

}
