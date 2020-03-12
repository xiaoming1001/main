package com.study.shiwu.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间转换工具类
 * @author ：ding
 * @date ：Created in 2019/12/7 9:47
 */
public class TimeUtil {

    /**
     * 获取当前秒级时间戳(10位)
     * @return
     */
    public static String getSecondsTimeStampStr(){
        Long secondsTimeStamp = System.currentTimeMillis() / 1000L;
        return secondsTimeStamp.toString();
    }

    /**
     * 获取当前秒级时间戳(10位)
     * @return
     */
    public static Integer getSecondsTimeStamp(){
        Long secondsTimeStamp = System.currentTimeMillis() / 1000L;
        return secondsTimeStamp.intValue();
    }

    /**
     * 秒级时间戳(10位)转成日期和时间字符串（年-月-日 小时：分钟：秒）
     * @param secondsTimeStamp 秒级时间戳(10位)字符串
     */
    public static String secondsTimeStampToDateAndTimeString(String secondsTimeStamp){
        if (secondsTimeStamp == null){
            return null;
        }
        long secondsTimeStampLong = Long.parseLong(secondsTimeStamp);
        Timestamp timestamp=new Timestamp(secondsTimeStampLong*1000);
        Date date = new Date(timestamp.getTime());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }

    /**
     * 秒级时间戳(10位)转成日期和时间字符串（年-月-日 小时：分钟：秒）
     * @param secondsTimeStamp 秒级时间戳(10位)字符串
     */
    public static String secondsTimeStampToDateAndTimeString(Integer secondsTimeStamp){
        if (secondsTimeStamp == null){
            return null;
        }
        long secondsTimeStampLong = new Long(secondsTimeStamp);
        Timestamp timestamp=new Timestamp(secondsTimeStampLong*1000);
        Date date = new Date(timestamp.getTime());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }

    /**
     * 秒级时间戳(10位)转成日期和时间Date类（年-月-日 小时：分钟：秒）
     * @param secondsTimeStamp 秒级时间戳(10位)
     */
    public static Date secondsTimeStampToDateAndTimeDate(Integer secondsTimeStamp){
        if (secondsTimeStamp == null){
            return null;
        }
        long secondsTimeStampLong = new Long(secondsTimeStamp);
        Timestamp timestamp=new Timestamp(secondsTimeStampLong*1000);
        Date date = new Date(timestamp.getTime());
        return date;
    }

    /**
     * 秒级时间戳(10位)转成日期（年-月-日）字符串
     * @param secondsTimeStamp 秒级时间戳(10位)字符串
     */
    public static String secondsTimeStampToDateString(String secondsTimeStamp){
        if (secondsTimeStamp == null){
            return null;
        }
        long secondsTimeStampLong = Long.parseLong(secondsTimeStamp);
        Date date = new Date(secondsTimeStampLong*1000);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

    /**
     * 通过时间字符串 计算时间戳
     * @param dateStr
     * @return
     * @throws Exception
     */
    public static Integer getTimeStampByDateStr(String dateStr) throws Exception{
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return new Long(format.parse(dateStr).getTime()/1000L).intValue();
    }

}
