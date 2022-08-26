package com.javarush.task.task32.task3211;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.security.MessageDigest;

/* 
Целостность информации
*/

public class Solution {
    public static void main(String... args) throws Exception {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(new String("test string"));
        oos.flush();
        System.out.println(compareMD5(bos, "5a47d12a2e3f9fecf2d9ba1fd98152eb")); //true

    }

    public static boolean compareMD5(ByteArrayOutputStream byteArrayOutputStream, String md5) throws Exception {
        MessageDigest md = MessageDigest.getInstance("md5");
        byte[] hash = md.digest(byteArrayOutputStream.toByteArray());
        StringBuilder hashHex = new StringBuilder();
        for (int i = 0; i < hash.length; i++) {
            int num = (hash[i] < 0) ? hash[i] + 256 : hash[i];
            String hex = Integer.toHexString(num);
            if (hex.length() < 2) {
                hashHex.append("0");
            }
            hashHex.append(hex);
        }
        return hashHex.toString().equals(md5);
    }
}
