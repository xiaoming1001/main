package com.study.shiwu.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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

  /*    SimpleDateFormat si = new SimpleDateFormat("yyyy/MM/dd");
        System.out.println("当前小时开始："+si.format(getCurrentHourStartTime()));
        System.out.println("当前小时结束："+si.format(getCurrentHourEndTime()));
        System.out.println("当前天开始："+si.format(getCurrentDayStartTime()));
        System.out.println("当前天时结束："+si.format(getCurrentDayEndTime()));
        System.out.println("当前周开始："+si.format(getCurrentWeekDayStartTime()));
        System.out.println("当前周结束："+si.format(getCurrentWeekDayEndTime()));

        System.out.println("当前月开始："+si.format(getCurrentMonthStartTime()));
        System.out.println("当前月结束："+si.format(getCurrentMonthEndTime()));

        System.out.println("当前季度开始："+si.format(getCurrentQuarterStartTime()));
        System.out.println("当前季度结束："+si.format(getCurrentQuarterStartTime()));

        System.out.println("当前半年/后半年开始："+si.format(getHalfYearStartTime()));
        System.out.println("当前半年/后半年结束："+si.format(getHalfYearEndTime()));

        System.out.println("当前年开始："+si.format(getCurrentYearStartTime()));
        System.out.println("当前年结束："+si.format(getCurrentYearEndTime()));
}*/

    /**
     * 获取 当前年、半年、季度、月、日、小时 开始结束时间
     */

    private final static SimpleDateFormat shortSdf = new SimpleDateFormat("yyyy-MM-dd");
    private final static SimpleDateFormat longHourSdf = new SimpleDateFormat("yyyy-MM-dd HH");;
    private final static SimpleDateFormat longSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");;


    /**
     * 获得本周的第一天，周一
     *
     * @return
     */
    public static Date getCurrentWeekDayStartTime() {
            Calendar curStartCal = Calendar.getInstance();
            Calendar cal = (Calendar) curStartCal.clone();
            try {
            cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
            //String firstDay = shortSdf.format(cal.getTime());
            } catch (Exception e) {
            e.printStackTrace();
            }
            return cal.getTime();
            }

    /**
     * 获得本周的最后一天，周日
     *
     * @return
     */
    public static Date getCurrentWeekDayEndTime() {
            Calendar curStartCal = Calendar.getInstance();
            Calendar cal = (Calendar) curStartCal.clone();
            try {
            cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
            // String firstDay = shortSdf.format(cal.getTime());
            cal.add(Calendar.DATE, 6);
            //String lastDay = shortSdf.format(cal.getTime());
            } catch (Exception e) {
            e.printStackTrace();
            }
            return cal.getTime();
            }

    /**
     * 获得本天的开始时间
     *
     * @return
     */
    public static Date getCurrentDayStartTime() {
            Date now = new Date();
            try {
            now = shortSdf.parse(shortSdf.format(now));
            } catch (Exception e) {
            e.printStackTrace();
            }
            return now;
            }

    /**
     * 获得本天的结束时间
     *
     * @return
     */
    public static Date getCurrentDayEndTime() {
            Date now = new Date();
            try {
            now = longSdf.parse(shortSdf.format(now) + " 23:59:59");
            } catch (Exception e) {
            e.printStackTrace();
            }
            return now;
            }

    /**
     * 获得本小时的开始时间
     *
     * @return
     */
    public static Date getCurrentHourStartTime() {
            Date now = new Date();
            try {
            now = longHourSdf.parse(longHourSdf.format(now));
            } catch (Exception e) {
            e.printStackTrace();
            }
            return now;
            }

    /**
     * 获得本小时的结束时间
     *
     * @return
     */
    public static Date getCurrentHourEndTime() {
            Date now = new Date();
            try {
            now = longSdf.parse(longHourSdf.format(now) + ":59:59");
            } catch (Exception e) {
            e.printStackTrace();
            }
            return now;
            }

    /**
     * 获得本月的开始时间
     *
     * @return
     */
    public static Date getCurrentMonthStartTime() {
            Calendar c = Calendar.getInstance();
            Date now = null;
            try {
            c.set(Calendar.DATE, 1);
            now = shortSdf.parse(shortSdf.format(c.getTime()));
            } catch (Exception e) {
            e.printStackTrace();
            }
            return now;
            }

    /**
     * 本月的结束时间
     *
     * @return
     */
    public static Date getCurrentMonthEndTime() {
            Calendar c = Calendar.getInstance();
            Date now = null;
            try {
            c.set(Calendar.DATE, 1);
            c.add(Calendar.MONTH, 1);
            c.add(Calendar.DATE, -1);
            now = longSdf.parse(shortSdf.format(c.getTime()) + " 23:59:59");
            } catch (Exception e) {
            e.printStackTrace();
            }
            return now;
            }

    /**
     * 当前年的开始时间
     *
     * @return
     */
    public static Date getCurrentYearStartTime() {
            Calendar c = Calendar.getInstance();
            Date now = null;
            try {
            c.set(Calendar.MONTH, 0);
            c.set(Calendar.DATE, 1);
            now = shortSdf.parse(shortSdf.format(c.getTime()));
            } catch (Exception e) {
            e.printStackTrace();
            }
            return now;
            }

    /**
     * 当前年的结束时间
     *
     * @return
     */
    public static Date getCurrentYearEndTime() {
            Calendar c = Calendar.getInstance();
            Date now = null;
            try {
            c.set(Calendar.MONTH, 11);
            c.set(Calendar.DATE, 31);
            now = longSdf.parse(shortSdf.format(c.getTime()) + " 23:59:59");
            } catch (Exception e) {
            e.printStackTrace();
            }
            return now;
            }

    /**
     * 当前季度的开始时间
     *
     * @return
     */
    public static Date getCurrentQuarterStartTime() {
            Calendar c = Calendar.getInstance();
            int currentMonth = c.get(Calendar.MONTH) + 1;
            Date now = null;
            try {
            if (currentMonth >= 1 && currentMonth <= 3)
            c.set(Calendar.MONTH, 0);
            else if (currentMonth >= 4 && currentMonth <= 6)
            c.set(Calendar.MONTH, 3);
            else if (currentMonth >= 7 && currentMonth <= 9)
            c.set(Calendar.MONTH, 4);
            else if (currentMonth >= 10 && currentMonth <= 12)
            c.set(Calendar.MONTH, 9);
            c.set(Calendar.DATE, 1);
            now = longSdf.parse(shortSdf.format(c.getTime()) + " 00:00:00");
            } catch (Exception e) {
            e.printStackTrace();
            }
            return now;
            }

    /**
     * 当前季度的结束时间
     *
     * @return
     */
    public static Date getCurrentQuarterEndTime() {
            Calendar c = Calendar.getInstance();
            int currentMonth = c.get(Calendar.MONTH) + 1;
            Date now = null;
            try {
            if (currentMonth >= 1 && currentMonth <= 3) {
            c.set(Calendar.MONTH, 2);
            c.set(Calendar.DATE, 31);
            } else if (currentMonth >= 4 && currentMonth <= 6) {
            c.set(Calendar.MONTH, 5);
            c.set(Calendar.DATE, 30);
            } else if (currentMonth >= 7 && currentMonth <= 9) {
            c.set(Calendar.MONTH, 8);
            c.set(Calendar.DATE, 30);
            } else if (currentMonth >= 10 && currentMonth <= 12) {
            c.set(Calendar.MONTH, 11);
            c.set(Calendar.DATE, 31);
            }
            now = longSdf.parse(shortSdf.format(c.getTime()) + " 23:59:59");
            } catch (Exception e) {
            e.printStackTrace();
            }
            return now;
            }

    /**
     * 获取前/后半年的开始时间
     *
     * @return
     */
    public static Date getHalfYearStartTime() {
            Calendar c = Calendar.getInstance();
            int currentMonth = c.get(Calendar.MONTH) + 1;
            Date now = null;
            try {
            if (currentMonth >= 1 && currentMonth <= 6) {
            c.set(Calendar.MONTH, 0);
            } else if (currentMonth >= 7 && currentMonth <= 12) {
            c.set(Calendar.MONTH, 6);
            }
            c.set(Calendar.DATE, 1);
            now = longSdf.parse(shortSdf.format(c.getTime()) + " 00:00:00");
            } catch (Exception e) {
            e.printStackTrace();
            }
            return now;

            }

    /**
     * 获取前/后半年的结束时间
     *
     * @return
     */
    public static Date getHalfYearEndTime() {
            Calendar c = Calendar.getInstance();
            int currentMonth = c.get(Calendar.MONTH) + 1;
            Date now = null;
            try {
             /*   if (currentMonth >= 1 && currentMonth <= 6) {
                    c.set(Calendar.MONTH, 5);
                    c.set(Calendar.DATE, 30);
                } else if (currentMonth >= 7 && currentMonth <= 12) {
                    c.set(Calendar.MONTH, 11);
                    c.set(Calendar.DATE, 31);
                }*/
            if (currentMonth >= 1 && currentMonth <= 6) {
            c.set(Calendar.MONTH, 11);
            c.set(Calendar.DATE, 31);

            } else if (currentMonth >= 7 && currentMonth <= 12) {
            c.set(Calendar.MONTH, 5);
            c.set(Calendar.DATE, 30);
            }
            now = longSdf.parse(shortSdf.format(c.getTime()) + " 23:59:59");
            } catch (Exception e) {
            e.printStackTrace();
            }
            return now;
            }

}
