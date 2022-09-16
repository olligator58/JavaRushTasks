package com.javarush.task.task39.task3913;

import java.nio.file.Paths;

public class Solution {
    public static void main(String[] args) {
        LogParser logParser = new LogParser(Paths.get("C:/work/JavaRush/files/logs/"));

        System.out.println(logParser.execute("get ip"));
        System.out.println(logParser.execute("get user"));
        System.out.println(logParser.execute("get date"));
        System.out.println(logParser.execute("get event"));
        System.out.println(logParser.execute("get status"));
        System.out.println(logParser.execute("get ip for user = \"Eduard Petrovich Morozko\""));
        System.out.println(logParser.execute("get ip for date = \"12.12.2013 21:56:30\""));
        System.out.println(logParser.execute("get ip for event = \"SOLVE_TASK\""));
        System.out.println(logParser.execute("get ip for status = \"FAILED\""));
        System.out.println(logParser.execute("get user for ip = \"127.0.0.1\""));
        System.out.println(logParser.execute("get user for event = \"WRITE_MESSAGE\""));
        System.out.println(logParser.execute("get user for status = \"ERROR\""));
        System.out.println(logParser.execute("get date for ip = \"127.0.0.1\""));
        System.out.println(logParser.execute("get date for user = \"Amigo\""));
        System.out.println(logParser.execute("get date for event = \"DONE_TASK\""));
        System.out.println(logParser.execute("get date for status = \"FAILED\""));
        System.out.println(logParser.execute("get event for ip = \"192.168.100.2\""));
        System.out.println(logParser.execute("get event for user = \"Eduard Petrovich Morozko\""));
        System.out.println(logParser.execute("get event for date = \"13.09.2013 5:04:50\""));
        System.out.println(logParser.execute("get event for status = \"FAILED\""));
        System.out.println(logParser.execute("get status for ip = \"127.0.0.1\""));
        System.out.println(logParser.execute("get status for user = \"Vasya Pupkin\""));
        System.out.println(logParser.execute("get status for date = \"30.01.2014 12:56:22\""));
        System.out.println(logParser.execute("get status for event = \"DONE_TASK\""));
        System.out.println(logParser.execute("get ip for user = \"Vasya Pupkin\" and date between \"11.12.2013 10:11:12\" and \"14.11.2015 07:08:01\""));
        System.out.println(logParser.execute("get ip for date = \"03.01.2014 03:45:23\" and date between \"11.12.2013 10:11:12\" and \"14.11.2015 07:08:01\""));
        System.out.println(logParser.execute("get ip for event = \"LOGIN\" and date between \"11.12.2013 10:11:12\" and \"14.11.2015 07:08:01\""));
        System.out.println(logParser.execute("get ip for status = \"FAILED\" and date between \"11.12.2013 10:11:12\" and \"14.11.2015 07:08:01\""));
        System.out.println(logParser.execute("get user for ip = \"127.0.0.1\" and date between \"11.12.2013 10:11:12\" and \"14.11.2015 07:08:01\""));
        System.out.println(logParser.execute("get user for date = \"14.11.2015 07:08:01\" and date between \"11.12.2013 10:11:12\" and \"14.11.2015 07:08:01\""));
        System.out.println(logParser.execute("get user for event = \"LOGIN\" and date between \"11.12.2013 10:11:12\" and \"14.11.2015 07:08:01\""));
        System.out.println(logParser.execute("get user for status = \"OK\" and date between \"11.12.2013 10:11:12\" and \"14.11.2015 07:08:01\""));
        System.out.println(logParser.execute("get date for ip = \"127.0.0.1\" and date between \"11.12.2013 10:11:12\" and \"14.11.2015 07:08:01\""));
        System.out.println(logParser.execute("get date for user = \"Vasya Pupkin\" and date between \"11.12.2013 10:11:12\" and \"14.11.2015 07:08:01\""));
        System.out.println(logParser.execute("get date for event = \"LOGIN\" and date between \"11.12.2013 10:11:12\" and \"14.11.2015 07:08:01\""));
        System.out.println(logParser.execute("get date for status = \"OK\" and date between \"11.12.2013 10:11:12\" and \"14.11.2015 07:08:01\""));
        System.out.println(logParser.execute("get event for ip = \"146.34.15.5\" and date between \"11.12.2013 10:11:12\" and \"14.11.2015 07:08:01\""));
        System.out.println(logParser.execute("get event for user = \"Vasya Pupkin\" and date between \"11.12.2013 10:11:12\" and \"14.11.2015 07:08:01\""));
        System.out.println(logParser.execute("get event for date = \"30.01.2014 12:56:22\" and date between \"11.12.2013 10:11:12\" and \"14.11.2015 07:08:01\""));
        System.out.println(logParser.execute("get event for status = \"OK\" and date between \"11.12.2013 10:11:12\" and \"14.11.2015 07:08:01\""));
        System.out.println(logParser.execute("get status for ip = \"192.168.100.2\" and date between \"11.12.2013 10:11:12\" and \"14.11.2015 07:08:01\""));
        System.out.println(logParser.execute("get status for user = \"Eduard Petrovich Morozko\" and date between \"11.12.2013 10:11:12\" and \"14.11.2015 07:08:01\""));
        System.out.println(logParser.execute("get status for date = \"11.12.2013 10:11:12\" and date between \"11.12.2013 10:11:12\" and \"14.11.2015 07:08:01\""));
        System.out.println(logParser.execute("get status for event = \"SOLVE_TASK\" and date between \"11.12.2013 10:11:12\" and \"14.11.2015 07:08:01\""));

    }
}