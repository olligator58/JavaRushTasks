package com.javarush.task.task21.task2101;

/* 
Определяем адрес сети
*/

public class Solution {
    public static void main(String[] args) {
        byte[] ip = new byte[]{(byte) 192, (byte) 168, 1, 2};
        byte[] mask = new byte[]{(byte) 255, (byte) 255, (byte) 254, 0};
        byte[] netAddress = getNetAddress(ip, mask);

        print(ip);          //11000000 10101000 00000001 00000010
        print(mask);        //11111111 11111111 11111110 00000000
        print(netAddress);  //11000000 10101000 00000000 00000000
    }

    public static byte[] getNetAddress(byte[] ip, byte[] mask) {
        byte[] result = new byte[ip.length];
        for (int i = 0; i < ip.length; i++) {
            result[i] = (byte) (ip[i] & mask[i]);
        }
        return result;
    }

    public static void print(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            int num = (bytes[i] >= 0) ? bytes[i] : 256 + bytes[i];
            result.append( numToBinary(num));
            result.append(" ");
        }
        System.out.println(result.toString().trim());
    }

    private static String numToBinary(int num) {
        StringBuilder result = new StringBuilder();
        while (num > 0) {
            result.insert(0, String.valueOf(num % 2));
            num /= 2;
        }

        while (result.length() < 8) {
            result.insert(0, "0");
        }
        return result.toString();
    }
}
