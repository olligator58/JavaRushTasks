package com.javarush.task.task39.task3912;

/* 
Максимальная площадь
*/

public class Solution {
    public static void main(String[] args) {
        System.out.println(maxSquare(new int[][]{
                {0, 0, 0, 0, 1, 1, 1},
                {0, 0, 0, 0, 0, 1, 0},
                {0, 1, 1, 1, 1, 0, 0},
                {0, 1, 1, 1, 1, 1, 0},
                {1, 1, 1, 1, 1, 0, 0},
                {0, 1, 1, 1, 1, 0, 1},
        }));
    }

    public static int maxSquare(int[][] matrix) {
        if (matrix == null || matrix.length < 2 || matrix[0].length < 2) {
            return 0;
        }

        int[][] m = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                m[i][j] = matrix[i][j];
            }
        }

        int maxWide = 0;
        for (int i = m.length - 2; i >= 0 ; i--) {
            for (int j = m[i].length - 2; j >= 0 ; j--) {
                if (m[i][j] != 0) {
                    m[i][j] = Math.min(Math.min(m[i][j + 1], m[i + 1][j]), m[i + 1][j + 1]) + 1;
                    if (m[i][j] > maxWide) {
                        maxWide = m[i][j];
                    }
                }
            }
        }
        return maxWide * maxWide;
    }
}
