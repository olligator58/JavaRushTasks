package com.javarush.task.task38.task3804;

public class ExceptionFactory {

    public static Throwable getException(Enum anEnum) {
        if (anEnum == null) {
            return new IllegalArgumentException();
        }
        String message = anEnum.toString().charAt(0) + anEnum.toString().substring(1).toLowerCase().replace("_", " ");
        switch (anEnum.getClass().getSimpleName()) {
            case "ApplicationExceptionMessage":
                return new Exception(message);
            case "DatabaseExceptionMessage":
                return new RuntimeException(message);
            case "UserExceptionMessage":
                return new Error(message);
            default:
                return new IllegalArgumentException();
        }
    }
}
