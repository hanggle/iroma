package com.hanggle.frames.util;

import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class CommonTest {

    @Test
    public void testma() {
        System.out.println(replaceSpace("We are happy."));
    }

    public String replaceSpace(String s) {
        StringBuilder sb = new StringBuilder();
        char[] strArr = s.toCharArray();
        for (char c : strArr) {
            if (c == ' ') {
                sb.append("%20");
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
