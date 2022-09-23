package com.javarush.task.task38.task3804;

/* 
Фабрика исключений
*/

public class Solution {
    public static Class getFactoryClass() {
        return ExceptionFactory.class;
    }

    public static void main(String[] args) {
        System.out.println(ExceptionFactory.getException(ApplicationExceptionMessage.UNHANDLED_EXCEPTION));
        System.out.println(ExceptionFactory.getException(DatabaseExceptionMessage.NO_RESULT_DUE_TO_TIMEOUT));
        System.out.println(ExceptionFactory.getException(UserExceptionMessage.USER_DOES_NOT_HAVE_PERMISSIONS));
    }
}