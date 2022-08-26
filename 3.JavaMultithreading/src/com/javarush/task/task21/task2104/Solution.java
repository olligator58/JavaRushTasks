package com.javarush.task.task21.task2104;

import java.util.HashSet;
import java.util.Set;

/* 
Equals and HashCode
*/

public class Solution {
    private final String first, last;

    public Solution(String first, String last) {
        this.first = first;
        this.last = last;
    }


    @Override
    public boolean equals(Object n) {
        if (this == n) {
            return true;
        }
        if (n == null || !(n instanceof Solution)) {
            return false;
        }
        Solution s = (Solution) n;
        if (first != null ? !first.equals(s.first) : s.first != null) return false;
        return (last != null) ? last.equals(s.last) : s.last == null;
    }

    @Override
    public int hashCode() {
        int result = (first != null) ? first.hashCode() : 0;
        result = 31 * result + ((last != null) ? last.hashCode() : 0);
        return result;
    }

    public static void main(String[] args) {
        Set<Solution> s = new HashSet<>();
        Solution s1 = new Solution("Donald", "Duck");
        s.add(s1);
        System.out.println(s1.hashCode());
        Solution s2 = new Solution("Donald", "Duck");
        System.out.println(s2.hashCode());
        System.out.println(s.contains(s2));
    }
}
