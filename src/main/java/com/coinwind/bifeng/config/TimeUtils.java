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

}
