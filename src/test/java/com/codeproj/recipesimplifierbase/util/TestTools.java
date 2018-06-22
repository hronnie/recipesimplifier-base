package com.codeproj.recipesimplifierbase.util;

public class TestTools {

    public static String generateNLengthString(int cnt) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < cnt; i++) {
            sb.append('a');
        }
        return sb.toString();
    }
}
