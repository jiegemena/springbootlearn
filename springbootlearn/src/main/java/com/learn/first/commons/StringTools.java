package com.learn.first.commons;

import org.springframework.util.DigestUtils;

public class StringTools{
    public static String getMD5(String encode){
        return DigestUtils.md5DigestAsHex(encode.getBytes());
    }
}