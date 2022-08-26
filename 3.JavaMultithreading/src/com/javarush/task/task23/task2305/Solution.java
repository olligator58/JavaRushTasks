package com.javarush.task.task23.task2305;

/* 
Inner
*/

public class Solution {
    public InnerClass[] innerClasses = new InnerClass[2];

    public class InnerClass {
    }

    public static Solution[] getTwoSolutions() {
        Solution[] result = new Solution[2];
        Solution s1 = new Solution();
        s1.innerClasses = new InnerClass[2];
        s1.innerClasses[0] = s1.new InnerClass();
        s1.innerClasses[1] = s1.new InnerClass();
        result[0] = s1;
        Solution s2 = new Solution();
        s2.innerClasses = new InnerClass[2];
        s2.innerClasses[0] = s2.new InnerClass();
        s2.innerClasses[1] = s2.new InnerClass();
        result[1] = s2;
        return result;
    }

    public static void main(String[] args) {
        Solution[] s = getTwoSolutions();
    }
}
