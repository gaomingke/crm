package com.kaishengit.util;

import java.io.UnsupportedEncodingException;

public class Strings {

    public static String toUTF8(String str) {
        try {
            return new String(str.getBytes("ISO8859-1"),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

}
