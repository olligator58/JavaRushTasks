package com.javarush.task.task22.task2209;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* 
Составить цепочку слов
*/

public class Solution {

    public static void main(String[] args) {
        String fileName = "";
        try (BufferedReader console = new BufferedReader(new InputStreamReader(System.in))) {
            fileName = console.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName));) {
            while (reader.ready()) {
                lines.addAll(Arrays.asList(reader.readLine().split("\\s")));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] words = new String[lines.size()];
        for (int i = 0; i < words.length; i++) {
            words[i] = lines.get(i);
        }

        StringBuilder result = getLine(words);
        System.out.println(result.toString());
    }

    public static StringBuilder getLine(String... words) {
        StringBuilder result = new StringBuilder();
        if (words.length != 0) {
            List<StringBuilder> chains = new ArrayList<>();
            Solution solution = new Solution();
            String[] myWords = words.clone();
            solution.permute(myWords, 0, myWords.length - 1, chains);
            if (chains.size() > 0) {
                int index = (int) (chains.size() * Math.random());
                result = chains.get(index);
            }
        }
        return result;
    }


    private void permute(String[] words, int l, int r, List<StringBuilder> chains) {
        if (l == r && isChain(words)) {
            StringBuilder sb = new StringBuilder();
            for (String word : words) {
                if (sb.length() != 0) {
                    sb.append(" ");
                }
                sb.append(word);
            }
            chains.add(sb);
        } else {
            for (int i = l; i <= r; i++) {
                words = swap(words, l, i);
                permute(words, l + 1, r, chains);
                words = swap(words, l, i);
            }

        }
    }

    private String[] swap(String[] words, int i, int j) {
        String temp;
        temp = words[i];
        words[i] = words[j];
        words[j] = temp;
        return words;
    }

    private boolean isChain(String[] words) {
        boolean result = false;
        for (int i = 0; i < words.length - 1; i++) {
            String last = words[i].substring(words[i].length() - 1).toLowerCase();
            String first = words[i + 1].substring(0, 1).toLowerCase();
            if (!last.equals(first)) {
                break;
            }
            if (i + 1 == words.length - 1) {
                result = true;
            }
        }
        return result;
    }
}




