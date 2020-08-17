package com.earth.shiro.config.util;

import org.springframework.util.StringUtils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * Created by tidus on 2018/8/22.
 */
public class DateUtils {

    public static final String YYYY_MM_DD = "yyyy-MM-dd";
    public static final String YYYY_MM_DD_HH_mm_ss = "yyyy-MM-dd HH:mm:ss";

    /**
     * 获取当天剩余秒数
     *
     * @return
     */
    public static long getTodayRemainSeconds() {
        Date now = new Date();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);

        // 第二天凌晨
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date todayEndDate = calendar.getTime();

        long nowTime = now.getTime();
        long endTime = todayEndDate.getTime();

        return (endTime - nowTime) / 1000;

    }

    /**
     * 字符串转日期适配方法
     *
     * @param dateStr 日期字符串
     */
    public static Date parseDate(String dateStr) {
        Date date = null;

        if (!StringUtils.isEmpty(dateStr)) {
            //判断是不是日期字符串，如Wed May 28 08:00:00 CST 2014
            if (dateStr.contains("CST")) {
                date = new Date(dateStr);
            } else {
                dateStr = dateStr.replace("年", " -").replace("月", " -").replace("日", "").replaceAll("/", "-").replaceAll("\\.", "-").trim();
                String pattern = "";

                //确定日期格式
                if (Pattern.compile("^[0-9]{4}-[0-9]{2}-[0-9]{2}.*").matcher(dateStr).matches()) {
                    pattern = "yyyy-MM-dd";
                } else if (Pattern.compile("^[0-9]{4}-[0-9]-[0-9]+.*||^[0-9]{4}-[0-9]+-[0-9].*").matcher(dateStr).matches()) {
                    pattern = "yyyy-M-d";
                } else if (Pattern.compile("^[0-9]{2}-[0-9]{2}-[0-9]{2}.*").matcher(dateStr).matches()) {
                    pattern = "yy-MM-dd";
                } else if (Pattern.compile("^[0-9]{2}-[0-9]-[0-9]+.*||^[0-9]{2}-[0-9]+-[0-9].*").matcher(dateStr).matches()) {
                    pattern = "yy-M-d";
                }


                //确定时间格式
                if (Pattern.compile(".*[ ][0-9]{2}").matcher(dateStr).matches()) {
                    pattern += " HH";
                } else if (Pattern.compile(".*[ ][0-9]{2}:[0-9]{2}").matcher(dateStr).matches()) {
                    pattern += " HH:mm";
                } else if (Pattern.compile(".*[ ][0-9]{2}:[0-9]{2}:[0-9]{2}").matcher(dateStr).matches()) {
                    pattern += " HH:mm:ss";
                } else if (Pattern.compile(".*[ ][0-9]{2}:[0-9]{2}:[0-9]{2}:[0-9]{0,3}").matcher(dateStr).matches()) {
                    pattern += " HH:mm:ss:sss";
                }

                if (!"".equals(pattern)) {
                    try {
                        date = new SimpleDateFormat(pattern).parse(dateStr);
                    } catch (ParseException e) {
                        e.printStackTrace();
                        return null;
                    }
                }
            }
        }
        return date;
    }



    /**
     *
     * @Title: dateToStr
     * @Description: 日期转字符串
     * @param @param date
     * @param @param parttern
     * @param @return
     * @return Date
     * @throws
     */
    public static String dateToStr(Date date ,String parttern){
        String s = null;
        try {
            if(null != date) {
                s = new SimpleDateFormat(parttern).format(date);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }


    /**
     * @param @param  date
     * @param @param  parttern
     * @param @return
     * @return Date
     * @throws
     * @Title: StrToDate
     * @Description: 字符串转换时间
     */
    public static Date strToDate(String date, String pattern) {
        Date myDate = null;
        if (null != date && !"".equals(date)) {
            try {
                myDate = new SimpleDateFormat(pattern).parse(date);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return myDate;
    }

    /**
     * @param @param
     * @param @param
     * @param @return
     * @return Timestamp
     * @throws
     * @Title: getCurrentTimestamp 获取当前时间
     * @Description:
     */
    public static Timestamp getCurrentTimestamp() {
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        return timestamp;
    }



}
