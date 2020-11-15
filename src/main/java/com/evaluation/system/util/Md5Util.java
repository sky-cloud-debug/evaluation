package com.evaluation.system.util;

import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Util {

    public static String EncodeByMd5(String str) {
        String newstr = "";
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            BASE64Encoder base64Encoder = new BASE64Encoder();
            newstr = base64Encoder.encode(md5.digest(str.getBytes("utf-8")));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newstr;
    }

    public static void main(String[] args) {
        System.out.println(EncodeByMd5("123"));
    }

}
