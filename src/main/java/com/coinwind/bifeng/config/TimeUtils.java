package com.coinwind.bifeng.config;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;

/**
 * 时间工具类
 */
public class TimeUtils {
    //2018-07-31 19:44:21
    public static final String DATE_TYPE = "yyyy-MM-dd HH:mm";

    /**
     * 将string转化为long
     *
     * @param beginTime
     * @return
     */
    public static long string2long(String beginTime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date date1 = null;
        try {
            date1 = sdf.parse(beginTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long sTime = date1.getTime();
        return sTime;
    }

    /**
     * 将long转为时分秒
     *
     * @param mSec
     * @return
     */
    public static String long2hms(Long mSec) {
        Long diffTime = mSec / 1000L;
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 2; i > 0; i--) {
            Long modTime = diffTime % 60;
            if (i == 2) {
                stringBuilder.insert(0, modTime);
            } else {
                stringBuilder.insert(0, modTime + ":");

            }
            diffTime /= 60;
        }
        stringBuilder.insert(0, diffTime + ":");
        return stringBuilder.toString();
    }

    /**
     * 将long转为年月日时分
     *
     * @param time
     * @return
     */
    public static String long2String(long time) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_TYPE);

        String timeStr = sdf.format(time);
        return timeStr;
    }

    /**
     * 转换毫秒数成“分、秒”，如“01:53”。若超过60分钟则显示“时、分、秒”，如“01:01:30
     *
     * @param time 待转换的毫秒数
     */
    public static String converLongTimeToStr(long time) {
        int ss = 1000;
        int mi = ss * 60;
        int hh = mi * 60;

        long hour = (time) / hh;
        long minute = (time - hour * hh) / mi;
        long second = (time - hour * hh - minute * mi) / ss;

        String strHour = hour < 10 ? "0" + hour : "" + hour;
        String strMinute = minute < 10 ? "0" + minute : "" + minute;
        String strSecond = second < 10 ? "0" + second : "" + second;
        if (hour > 0) {
            return strHour + ":" + strMinute + ":" + strSecond;
        } else {
            return "00:" + strMinute + ":" + strSecond;
        }
    }

    public static String long2timeBySecond(long secend) {
        return long2timeByMilliSeconds(secend * 1000);
    }

    public static String long2timeByMilliSeconds(long milliseconds) {
        String timeStr = null;
        Date date = new Date();
        date.setTime(mZeroTime + milliseconds);
        timeStr = mSdf.format(date);
        System.out.println(timeStr);
        return timeStr;
    }

    private static SimpleDateFormat mSdf = new SimpleDateFormat("HH:mm:ss");
    private static long mZeroTime = getZeroTime();

    private static long getZeroTime() {
        long zeroTime = 0;
        try {
            zeroTime = mSdf.parse("00:00:00").getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return zeroTime;
    }


}
