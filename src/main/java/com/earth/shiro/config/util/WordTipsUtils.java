package com.earth.shiro.config.util;

import org.apache.commons.lang3.StringUtils;

import java.text.NumberFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 此工具类是为了字母选项规则
 */
public class WordTipsUtils {


    private static String chars = "abcdefghijklmnopqrstuvwxyz";

    private static Random random = new Random();


    /**
     * * 1-10个字母，取单词字母加上剩余字母（不包括单词字母），凑成15个字母，随机排序
     * * 10-15个字母，取单词字母加上剩余字母（不包括单词字母），凑成15个字母，随机排序
     * * 16-20个字母，取单词字母加上剩余字母（不包括单词字母），凑成20个字母，随机排序
     * * 21-25个字母，取单词字母加上剩余字母（不包括单词字母），凑成25个字母，随机排序
     *
     * @param content
     * @return
     */
    public static List<String> getOptionsByContent(String content) {

        //剩余字母长度
        int len = content.length();

        //除了单词包含字母外的字母
        String leave = delContent(content);

        if (len <= 10) {

            content += randomString(leave, 15 - len);

        } else if (len <= 15) {

            content += randomString(leave, 15 - len);

        } else if (len <= 20) {

            content += randomString(leave, 20 - len);

        } else if (len <= 25) {

            content += randomString(leave, 25 - len);

        } else {
            int num = 0;
            if(content.length()/5 == 0) {
                num = content.length() - len;

            }else {
                num=(((content.length() / 5) + 1) * 5) - len;
            }
            content += randomString(leave, num);
        }

        List<String> result = Arrays.asList(content.split(""));

        Collections.shuffle(result);

        return result;
    }

    /**
     * 指定字符串,随机获取指定长度的字符串
     *
     * @param chars
     * @param len
     * @return
     */
    public static String randomString(String chars, int len) {

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < len; i++) {

            sb.append(chars.charAt(random.nextInt(chars.length())));
        }

        return sb.toString();
    }

    /**
     * 获取给定字符串字母以外的字母
     *
     * @param content
     * @return
     */
    public static String delContent(String content) {

        String result = chars;

        for (int i = 0; i < content.length(); i++) {

            String char1 = String.valueOf(content.charAt(i));

            if (result.contains(char1)) {

                result = result.replace(char1, "");
            }
        }

        return result;
    }

    /**
     * 去除单词中重复字母
     *
     * @param content
     * @return
     */
    public static String delRepeat(String content) {

        char[] str = content.toCharArray();

        String result = "";

        for (int i = 0; i < str.length; i++) {

            String tmp = String.valueOf(str[i]);

            if (result.contains(tmp)) {

            } else {
                result += tmp;
            }
        }

        return result;
    }

    /**
     * 标识用户输入提示
     *
     * @param content      原正确单词
     * @param userSpelling 用户输入 默认长度小于等于正确单词
     * @return
     */
    public static String getUserSpelling(String content, String userSpelling) {

        StringBuilder stringBuilder = new StringBuilder();

        int len = 0;

        if (StringUtils.isNotBlank(userSpelling)) {
            len = userSpelling.length();
        }

        for (int i = 0; i < content.length(); i++) {
            char v1 = content.charAt(i);
            char userv1 = 0;
            if (len > 0 && i < len) {
                userv1 = userSpelling.charAt(i);
            }
            if (v1 == userv1) {
                stringBuilder.append(v1);
            } else {
                stringBuilder.append("<span>");
                if (userv1 != 0) {
                    stringBuilder.append(userv1);
                }
                stringBuilder.append("</span>");
            }
        }

        return stringBuilder.toString();
    }

    /**
     * 获取答案列表
     *
     * @param words  总单词列表  练习数据、包数据、词库数据
     * @param answer 正确答案
     * @return
     */
    public static List<String> getWords(List<String> words, String answer) {
        //不能直接操作words，使用一个临时数组处理
        List<String> tmp_words=new ArrayList<>();
        for (String s:words){
            tmp_words.add(s);
        }
        List<String> result = new ArrayList<>();
        if (tmp_words.contains(answer)) {
            tmp_words.remove(answer);
        }
        int size = tmp_words.size();
        Set<String> tmp = new HashSet(tmp_words);
        int no_repeat = tmp.size();
        if (no_repeat < 3) {
            //理论上不会小于3
            //如果words存在重复数据,随机取3个出来
            for (int i = 0; i < 4; i++) {
                int n = new Random().nextInt(size);
                String w1 = tmp_words.get(n);
                result.add(w1);
                if (result.size() == 3) {
                    break;
                }
            }
        } else {
            List<String> a = new ArrayList<>(tmp);
            //打乱顺序
            Collections.shuffle(a);
            for (String w : a) {
                result.add(w);
                if (result.size() == 3) {
                    break;
                }
            }
        }
        //加上答案
        result.add(answer);
        //打乱排序
        Collections.shuffle(result);
        return result;
    }

    /**
     * 计算正确率百分比
     *
     * @param correct
     * @param total
     * @return
     */
    public static int getCorrectPercent(int correct, int total) {
        if(correct ==0 && total == 0){
            return 0;
        }
        NumberFormat numberFormat = NumberFormat.getNumberInstance();
        numberFormat.setMaximumFractionDigits(0);
        String result = numberFormat.format((float) correct / (float) total * 100);
        return Integer.valueOf(result);
    }

    /**
     * 不区分大小写替换答案为-
     *
     * @param source
     * @param oldstr
     * @param newstr
     * @return
     */
    public static String ignoreCaseReplace(String source, String oldstr, String newstr) {

        Pattern p = Pattern.compile(oldstr, Pattern.CASE_INSENSITIVE);

        Matcher m = p.matcher(source);

        String ret = m.replaceFirst(newstr);

        return ret;
    }

    public static void main(String[] args) {
        System.out.println(randomString("abcdefg", 3));
        System.out.println(delContent("water"));
        System.out.println(getOptionsByContent("water"));
        System.out.println(delRepeat("happy"));
        System.out.println(getCorrectPercent(1, 7));
        System.out.println(getUserSpelling("wing", "llll"));
        System.out.println(ignoreCaseReplace("Hello world hello,nihao.", "hello", "-"));
    }
}
