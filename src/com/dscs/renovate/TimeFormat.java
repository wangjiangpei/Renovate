package com.dscs.renovate;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created name : wjp
 * time :  2018/1/9 0009.
 */

public class TimeFormat {
    private static DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static String time2String(long time) {
        return df.format(time);
    }

    public static String time2String(String format, long time) {
        return new SimpleDateFormat(format).format(time);
    }

    public static String nowTimeStr() {
        return df.format(new Date());
    }

    public static String nowTimeStr2not() {
        return new SimpleDateFormat("yyyyMMdd").format(new Date());
    }

    public static String nowTimeStrDay() {
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    }

    public static String nowTimeDayCodeStr() {
        return new SimpleDateFormat("yyyyMMdd").format(new Date());
    }

    public static String nowTime_yyyy_mm_dd_hhmmss() {
        return new SimpleDateFormat("yyyy-MM-dd HHmmss").format(new Date());
    }
}
