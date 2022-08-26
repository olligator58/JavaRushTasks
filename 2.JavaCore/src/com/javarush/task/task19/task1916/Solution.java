package com.javarush.task.task19.task1916;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/* 
Отслеживаем изменения
*/

public class Solution {
    public static List<LineItem> lines = new ArrayList<LineItem>();

    public static void main(String[] args) {
        String fileName1 = "";
        String fileName2 = "";

        try (BufferedReader console = new BufferedReader(new InputStreamReader(System.in))) {
            fileName1 = console.readLine();
            fileName2 = console.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String text1 = "";
        try (FileReader reader1 = new FileReader(fileName1)) {
            while (reader1.ready()) {
                text1 += (char) reader1.read();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<String> lines1 = new ArrayList<>();
        for (String line : text1.replace("\r", "").split("\n")) {
            lines1.add(line);
        }

        String text2 = "";
        try (FileReader reader2 = new FileReader(fileName2)) {
            while (reader2.ready()) {
                text2 += (char) reader2.read();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<String> lines2 = new ArrayList<>();
        for (String line : text2.replace("\r", "").split("\n")) {
            lines2.add(line);
        }

        int i = 0;
        while (i < lines1.size() && i < lines2.size()) {
            if (!lines1.get(i).equals(lines2.get(i))) {
                if (i + 1 < lines1.size() && lines1.get(i + 1).equals(lines2.get(i))) {
                    lines2.add(i, null);
                } else if (i + 1 < lines2.size() && lines2.get(i + 1).equals(lines1.get(i))) {
                    lines1.add(i, null);
                }
            }
            i++;
        }

        if (lines1.size() < lines2.size()) {
            lines1.add(null);
        } else if (lines2.size() < lines1.size()) {
            lines2.add(null);
        }

        for (int j = 0; j < lines1.size(); j++) {
            if (lines1.get(j) != null && lines2.get(j) != null && lines1.get(j).equals(lines2.get(j))) {
                lines.add(new LineItem(Type.SAME, lines1.get(j)));
            } else if (lines1.get(j) == null) {
                lines.add(new LineItem(Type.ADDED, lines2.get(j)));
            } else if (lines2.get(j) == null) {
                lines.add(new LineItem(Type.REMOVED, lines1.get(j)));
            }
        }
    }


    public static enum Type {
        ADDED,        //добавлена новая строка
        REMOVED,      //удалена строка
        SAME          //без изменений
    }

    public static class LineItem {
        public Type type;
        public String line;

        public LineItem(Type type, String line) {
            this.type = type;
            this.line = line;
        }
    }
}
