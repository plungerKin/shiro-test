package com.earth.shiro.config.util;

import org.apache.commons.lang3.StringUtils;

public class EmojiUtils {

    public static final String REPLACE_CHAR = "?";
    /**
     * 检测是否有emoji字符
     *
     * @param source
     * @return 一旦含有就抛出
     */
    public static boolean containsWord(String source) {
        if (StringUtils.isBlank(source)) {
            return false;
        }

        int len = source.length();
        for (int i = 0; i < len; i++) {
            char codePoint = source.charAt(i);
            if (isNotEmojiCharacter(codePoint)) {
                return true;
            }
        }
        return false;
    }


    private static boolean isNotEmojiCharacter(char codePoint) {
        return (codePoint == 0x0) ||
                (codePoint == 0x9) ||
                (codePoint == 0xA) ||
                (codePoint == 0xD) ||
                ((codePoint >= 0x20) && (codePoint <= 0xD7FF)) ||
                ((codePoint >= 0xE000) && (codePoint <= 0xFFFD)) ||
                ((codePoint >= 0x10000) && (codePoint <= 0x10FFFF));
    }

    /**
     * 过滤emoji 或者 其他非文字类型的字符
     *
     * @param source
     * @return
     */
    public static String emojiCharReplace(String source, String replaceStr) {

        try {
            if (!containsWord(source)) {
                return source;//如果不包含，返回原字符串
            }
            //到这里铁定包含
            StringBuilder buf = null;
            int len = source.length();

            for (int i = 0; i < len; i++) {
                char codePoint = source.charAt(i);
                if (isNotEmojiCharacter(codePoint)) {
                    // 不包含的字节 , 就append到buf内
                    if (buf == null) {
                        buf = new StringBuilder(source.length());
                    }
                    buf.append(codePoint);
                } else {
                    // 这里是已经包含了特殊字符的话 , 使用replaceStr参数替换
                    if (buf == null) {
                        buf = new StringBuilder(source.length());
                    }
                    buf.append(replaceStr);
                }
            }
            if (buf == null) {
                return "";//如果没有可能到这步吧！
            } else {
                return buf.toString();
            }
        } catch (Exception e) {
            return source;
        }
    }

}
