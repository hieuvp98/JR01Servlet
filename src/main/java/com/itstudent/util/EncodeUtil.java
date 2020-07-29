package com.itstudent.util;

public class EncodeUtil {

    private static final String secret = "itstudent";
    private static final int count = 3;

    public static String encode(String input) {
        StringBuilder output = new StringBuilder(input + "-" + secret);
        for (int i = 0; i < output.length(); i++) {
            output.setCharAt(i, (char)(output.charAt(i) + count));
        }
        return output.toString();
    }

    public static String decode(String code) {
        StringBuilder origin = new StringBuilder(code);
        for (int i = 0; i < origin.length(); i++) {
            origin.setCharAt(i, (char)(origin.charAt(i) - count));
        }
        if (origin.toString().contains(secret)){
            return origin.toString().split("-")[0];
        }
        return null;
    }

    public static void main(String[] args) {
        String username = "admin";
        String encode = EncodeUtil.encode(username);
        System.out.println(encode);
        System.out.println(EncodeUtil.decode(encode));
    }
}
