package com.javarush.task.task19.task1918;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/* 
Знакомство с тегами
*/

public class Solution {

    public static class TagPosition {
        private int position;
        private String type;

        public TagPosition (int position, String type) {
            this. position = position;
            this.type = type;
        }
    }

    public static class TagBorders {
        private int begPosition;
        private int endPosition;

        public TagBorders(int begPosition, int endPosition) {
            this.begPosition = begPosition;
            this.endPosition = endPosition;
        }
    }

    public static void main(String[] args) {
        Set<TagPosition> tags = new TreeSet<>((t1, t2) -> (t1.position - t2.position));
        Set<TagBorders> borders = new TreeSet<>((b1, b2) -> (b1.begPosition - b2.begPosition));
        List<TagPosition> stack = new ArrayList<>();


        if (args.length != 1) {
            throw new RuntimeException("В параметре должен быть передан тэг");
        }

        String fileName = "";
        try (BufferedReader console = new BufferedReader(new InputStreamReader(System.in))) {
            fileName = console.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String text = "";
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            while (reader.ready()) {
                text += reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        String tagBegin = "<" + args[0];
        String tagEnd = "</" + args[0] + ">";

        int currIndex = 0;
        int index;
        while ((index = text.indexOf(tagBegin, currIndex)) > -1) {
            tags.add(new TagPosition(index, "BEGIN"));
            currIndex = index + 1;
        }

        currIndex = 0;
        while ((index = text.indexOf(tagEnd, currIndex)) > -1) {
            tags.add(new TagPosition(index, "END"));
            currIndex = index + 1;
        }

        for (TagPosition tag: tags) {
            if (tag.type.equals("BEGIN")) {
                stack.add(0, tag);
            } else {
                TagPosition fromStack = stack.remove(0);
                borders.add(new TagBorders(fromStack.position, tag.position));
            }
        }

        for (TagBorders border: borders) {
            System.out.println(text.substring(border.begPosition, border.endPosition + tagEnd.length()));
        }
    }
}
