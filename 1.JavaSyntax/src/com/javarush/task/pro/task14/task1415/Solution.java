package com.javarush.task.pro.task14.task1415;

public class Solution {
    public static void main(String[] args) {
        MyStack myStack = new MyStack();
        System.out.println(myStack.empty());
        myStack.push("1");
        myStack.push("2");
        myStack.push("3");
        myStack.push("4");
        myStack.push("5");
        System.out.println(myStack.storage);
        System.out.println(myStack.pop());
        System.out.println(myStack.storage);
        System.out.println(myStack.peek());
        System.out.println(myStack.empty());
        System.out.println(myStack.search("1"));
        System.out.println(myStack.search("3"));
        System.out.println(myStack.search("6"));
    }
}
