package com.earth.shiro.config.util;


import org.springframework.util.StringUtils;

import java.util.Base64;


/**
 * Base64Util工具类
 */
public class Base64Util {


    public static String base64EncodeForQiNiu(String str) {
        byte[] bytes = str.getBytes();
        String s = Base64.getEncoder().encodeToString(bytes);
        //将字符串中的加号+换成中划线-，并且将斜杠/换成下划线_
        String replace = StringUtils.replace(s, "+", "-");
        String fianl = StringUtils.replace(replace, "/", "_");
        return fianl;
    }

    public static String base64DecodeForQiNiu(String str){
        String replace = StringUtils.replace(str, "-", "+");
        String fanil = StringUtils.replace(replace, "/", "_");
        byte[] decode = Base64.getDecoder().decode(fanil);
        String s = new String(decode);
        return s;
    }


}
