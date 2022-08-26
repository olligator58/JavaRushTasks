package com.javarush.task.task20.task2027;

import java.util.ArrayList;
import java.util.List;

/* 
Кроссворд
*/

public class Solution {
    public static void main(String[] args) {
        int[][] crossword = new int[][]{
                {'f', 'd', 'e', 'r', 'l', 'k'},
                {'u', 's', 'a', 'm', 'e', 'o'},
                {'l', 'n', 'g', 'r', 'o', 'v'},
                {'m', 'l', 'p', 'r', 'r', 'h'},
                {'p', 'o', 'e', 'e', 'j', 'j'}
        };
        List<Word> words = detectAllWords(crossword, "home", "same");
        for (Word w : words) {
            System.out.println(w);
        }

        /*
Ожидаемый результат
home - (5, 3) - (2, 0)
same - (1, 1) - (4, 1)
         */
    }

    public static List<Word> detectAllWords(int[][] crossword, String... words) {
        List<Word> result = new ArrayList<>();
        for (String word : words) {
            List<Position> startPositions = getStartPositions(crossword, word.charAt(0));
            for (Position sp : startPositions) {
                List<List<Position>> routes = makeRoutes(crossword, word, sp);
                for (List<Position> route : routes) {
                    Word w = checkWord(crossword, word, route);
                    if (w != null) {
                        result.add(w);
                    }
                    if (word.length() == 1) {
                        break;
                    }
                }
            }
        }
        return result;
    }

    private static List<Position> getStartPositions(int[][] crossword, char letter) {
        List<Position> result = new ArrayList<>();
        for (int i = 0; i < crossword.length; i++) {
            for (int j = 0; j < crossword[i].length; j++) {
                if (crossword[i][j] == letter) {
                    result.add(new Position(j, i));
                }
            }
        }
        return result;
    }

    private static List<List<Position>> makeRoutes(int[][] crossword, String word, Position sp) {
        List<List<Position>> result = new ArrayList();
        //move right
        List<Position> routeRight = new ArrayList<>();
        if (sp.x + (word.length() - 1) < crossword[sp.y].length) {
            for (int i = sp.x; i < sp.x + word.length(); i++) {
                routeRight.add(new Position(i, sp.y));
            }
        }
        result.add(routeRight);

        //move left
        List<Position> routeLeft = new ArrayList<>();
        if (sp.x - (word.length() - 1) >= 0) {
            for (int i = sp.x; i > sp.x - word.length(); i--) {
                routeLeft.add(new Position(i, sp.y));
            }
        }
        result.add(routeLeft);

        //move up
        List<Position> routeUp = new ArrayList<>();
        if (sp.y - (word.length() - 1) >= 0) {
            for (int i = sp.y; i > sp.y - word.length(); i--) {
                routeUp.add(new Position(sp.x, i));
            }
        }
        result.add(routeUp);

        //move down
        List<Position> routeDown = new ArrayList<>();
        if (sp.y + (word.length() - 1) < crossword.length) {
            for (int i = sp.y; i < sp.y + word.length(); i++) {
                routeDown.add(new Position(sp.x, i));
            }
        }
        result.add(routeDown);

        //move right and down
        List<Position> routeRightDown = new ArrayList<>();
        if ((sp.x + (word.length() - 1) < crossword[sp.y].length) && (sp.y + (word.length() - 1) < crossword.length)) {
            int y = sp.y;
            for (int i = sp.x; i < sp.x + word.length(); i++) {
                routeRightDown.add(new Position(i, y));
                y++;
            }
        }
        result.add(routeRightDown);

        //move right and up
        List<Position> routeRightUp = new ArrayList<>();
        if ((sp.x + (word.length() - 1) < crossword[sp.y].length) && (sp.y - (word.length() - 1) >= 0)) {
            int y = sp.y;
            for (int i = sp.x; i < sp.x + word.length(); i++) {
                routeRightUp.add(new Position(i, y));
                y--;
            }
        }
        result.add(routeRightUp);

        //move left and down
        List<Position> routeLeftDown = new ArrayList<>();
        if ((sp.x - (word.length() - 1) >= 0) && (sp.y + (word.length() - 1) < crossword.length)) {
            int y = sp.y;
            for (int i = sp.x; i > sp.x - word.length(); i--) {
                routeLeftDown.add(new Position(i, y));
                y++;
            }
        }
        result.add(routeLeftDown);

        //move left and up
        List<Position> routeLeftUp = new ArrayList<>();
        if ((sp.x - (word.length() - 1) >= 0) && (sp.y - (word.length() - 1) >= 0)) {
            int y = sp.y;
            for (int i = sp.x; i > sp.x - word.length(); i--) {
                routeLeftUp.add(new Position(i, y));
                y--;
            }
        }
        result.add(routeLeftUp);

        return result;
    }

    private static Word checkWord(int[][] crossword, String word, List<Position> route) {
        Word result = null;
        int i = 0;
        for (Position p : route) {
            if (word.charAt(i) == crossword[p.y][p.x]) {
                i++;
            } else {
                break;
            }
        }
        if (i == word.length()) {
            result = new Word(word);
            result.setStartPoint(route.get(0).x, route.get(0).y);
            result.setEndPoint(route.get(route.size() - 1).x, route.get(route.size() - 1).y);
        }
        return result;
    }


    public static class Position {
        private int x;
        private int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static class Word {
        private String text;
        private int startX;
        private int startY;
        private int endX;
        private int endY;

        public Word(String text) {
            this.text = text;
        }

        public void setStartPoint(int i, int j) {
            startX = i;
            startY = j;
        }

        public void setEndPoint(int i, int j) {
            endX = i;
            endY = j;
        }

        @Override
        public String toString() {
            return String.format("%s - (%d, %d) - (%d, %d)", text, startX, startY, endX, endY);
        }
    }
}
