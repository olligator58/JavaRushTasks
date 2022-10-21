package com.javarush.task.task40.task4011;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/* 
Свойства URL
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        decodeURLString("https://www.amrood.com/index.htm?language=en#j2se");
    }

    public static void decodeURLString(String s) throws MalformedURLException {
        try {
            URL url = new URL(s);
            System.out.println(String.format("- %s", url.getProtocol()));
            System.out.println(String.format("- %s", url.getAuthority()));
            System.out.println(String.format("- %s", url.getFile()));
            System.out.println(String.format("- %s", url.getHost()));
            System.out.println(String.format("- %s", url.getPath()));
            System.out.println(String.format("- %s", url.getPort()));
            System.out.println(String.format("- %s", url.getDefaultPort()));
            System.out.println(String.format("- %s", url.getQuery()));
            System.out.println(String.format("- %s", url.getRef()));
        } catch (MalformedURLException e) {
            System.out.println(String.format("Parameter %s is not a valid URL.", s));
        }
    }
}

