/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.sourcod.com
 */

package com.tlkj.cod.common;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * 日期工具类
 * @author sourcod
 * @version 1.0
 * @className CodCommonDate
 * @date 2016-12-07 13:31:55
 */
public class CodCommonDate {
    
    /**
     * Date format pattern  this is often used.
     */
    public static final String PATTERN_YMD = "yyyy-MM-dd";
    
    /**
     * Date format pattern  this is often used.
     */
    public static final String PATTERN_YMDHMS = "yyyy-MM-dd HH:mm:ss";

    /**
     * 日期比较格式
     */
    public static final String PATTERN_DIFF = "yyyy'Y'MM'M'dd'D'HH'H'mm'm'ss'S'SSS'MS'";

    /**
     * Formats the given date according to the YMD pattern.
     * 
     * @param date The date to format.
     * @return An YMD formatted date string.
     * 
     * @see #PATTERN_YMD
     */
    public static String formatDate(Date date) {
        return formatDate(date, PATTERN_YMD);
    }

    /**
     * Formats the given date according to the YMD pattern.
     *
     * @param date The date to format.
     * @return An YMD formatted date string.
     *
     * @see #PATTERN_YMD
     */
    public static String formatDate(String date) {
        return formatDate(parseDate(date, PATTERN_YMD), PATTERN_YMD);
    }

    /**
     * Formats the given date according to the YMD pattern.
     *
     * @param date The date to format.
     * @return An YMD formatted date string.
     *
     * @see #PATTERN_YMD
     */
    public static String formatDate(String date, String pattern) {
        return formatDate(parseDate(date, pattern), pattern);
    }
    
    /**
     * Formats the given date according to the specified pattern.  The pattern
     * must conform to that used by the {@link SimpleDateFormat simple date
     * format} class.
     * 
     * @param date The date to format.
     * @param pattern The pattern to use for formatting the date.  
     * @return A formatted date string.
     * 
     * @throws IllegalArgumentException If the given date pattern is invalid.
     * 
     * @see SimpleDateFormat
     */
    public static String formatDate(Date date, String pattern) {
        if (date == null){
            throw new IllegalArgumentException("date is null");
        }
        if (pattern == null){
            throw new IllegalArgumentException("pattern is null");
        }

        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        return formatter.format(date);
    }
    
    /**
     * Parses a date value.  The format used for parsing the date value are retrieved from
     * the default PATTERN_YMD.
     *
     * @param dateValue the date value to parse
     * 
     * @return the parsed date
     * 
     * @throws IllegalArgumentException If the given dateValue is invalid.
     */
    public static Date parseDate(String dateValue) {
        return parseDate(dateValue, null);
    }
    
    /**
     * Parses the date value using the given date format.
     * 
     * @param dateValue the date value to parse
     * @param dateFormat the date format to use
     * 
     * @return the parsed date. if parse is failed , return null
     * 
     * @throws IllegalArgumentException If the given dateValue is invalid.
     */
    public static Date parseDate(String dateValue, String dateFormat) {
        if (dateValue == null) {
            throw new IllegalArgumentException("dateValue is null");
        }
        if (dateFormat == null) {
            dateFormat = PATTERN_YMD;
        }
        
        SimpleDateFormat df = new SimpleDateFormat(dateFormat);
        Date result = null;
        try {
            result = df.parse(dateValue);
        }
        catch (ParseException pe) {
            // 日期型字符串格式错误
            pe.printStackTrace();
        }
        return result;
    }
    
    /**
     * Adds a number of years to a date returning a new object.
     * The original date object is unchanged.
     *
     * @param date  the date, not null
     * @param amount  the amount to add, may be negative
     * @return the new date object with the amount added
     * @throws IllegalArgumentException if the date is null
     */
    public static Date addYears(Date date, int amount) {
        return add(date, Calendar.YEAR, amount);
    }
    
    /**
     * Adds a number of years to a timestamp returning a new object.
     * The original timestamp object is unchanged.
     *
     * @param timestamp  the timestamp, not null
     * @param amount  the amount to add, may be negative
     * @return the new timestamp object with the amount added
     * @throws IllegalArgumentException if the timestamp is null
     */
    public static Timestamp addYears(Timestamp timestamp, int amount) {
        return add(timestamp, Calendar.YEAR, amount);
    }
    
    //-----------------------------------------------------------------------
    /**
     * Adds a number of months to a date returning a new object.
     * The original date object is unchanged.
     *
     * @param date  the date, not null
     * @param amount  the amount to add, may be negative
     * @return the new date object with the amount added
     * @throws IllegalArgumentException if the date is null
     */
    public static Date addMonths(Date date, int amount) {
        return add(date, Calendar.MONTH, amount);
    }
    
    /**
     * Adds a number of months to a timestamp returning a new object.
     * The original timestamp object is unchanged.
     *
     * @param timestamp  the timestamp, not null
     * @param amount  the amount to add, may be negative
     * @return the new timestamp object with the amount added
     * @throws IllegalArgumentException if the timestamp is null
     */
    public static Timestamp addMonths(Timestamp timestamp, int amount) {
        return add(timestamp, Calendar.MONTH, amount);
    }
    
    //-----------------------------------------------------------------------
    /**
     * Adds a number of days to a date returning a new object.
     * The original date object is unchanged.
     *
     * @param date  the date, not null
     * @param amount  the amount to add, may be negative
     * @return the new date object with the amount added
     * @throws IllegalArgumentException if the date is null
     */
    public static Date addDays(Date date, int amount) {
        return add(date, Calendar.DATE, amount);
    }
    
    /**
     * Adds a number of days to a timestamp returning a new object.
     * The original timestamp object is unchanged.
     *
     * @param timestamp  the timestamp, not null
     * @param amount  the amount to add, may be negative
     * @return the new timestamp object with the amount added
     * @throws IllegalArgumentException if the timestamp is null
     */
    public static Timestamp addDays(Timestamp timestamp, int amount) {
        return add(timestamp, Calendar.DATE, amount);
    }
    
    //-----------------------------------------------------------------------
    /**
     * Adds a number of minutes to a timestamp returning a new object.
     * The original timestamp object is unchanged.
     *
     * @param timestamp  the timestamp, not null
     * @param amount  the amount to add, may be negative
     * @return the new timestamp object with the amount added
     * @throws IllegalArgumentException if the timestamp is null
     */
    public static Timestamp addMinutes(Timestamp timestamp, int amount) {
        return add(timestamp, Calendar.MINUTE, amount);
    }
    
    /**
     * Adds a number of days to current time returning a new object.
     *
     * @param amount  the amount to add, may be negative
     * @return the new timestamp object with the amount added
     */
    public static Timestamp addDays(int amount) {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, amount);
        return new Timestamp(c.getTimeInMillis());
    }
    
    //-----------------------------------------------------------------------
    /**
     * Adds to a date returning a new object.
     * The original date object is unchanged.
     *
     * @param date  the date, not null
     * @param calendarField  the calendar field to add to
     * @param amount  the amount to add, may be negative
     * @return the new date object with the amount added
     * @throws IllegalArgumentException if the date is null
     */
    public static Date add(Date date, int calendarField, int amount) {
        if (date == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(calendarField, amount);
        return c.getTime();
    }
    
    /**
     * Adds to a timestamp returning a new object.
     * The original timestamp object is unchanged.
     *
     * @param timestamp  the timestamp, not null
     * @param calendarField  the calendar field to add to
     * @param amount  the amount to add, may be negative
     * @return the new timestamp object with the amount added
     * @throws IllegalArgumentException if the timestamp is null
     */
    public static Timestamp add(Timestamp timestamp, int calendarField, int amount) {
        if (timestamp == null) {
            throw new IllegalArgumentException("The timestamp must not be null");
        }
        Calendar c = Calendar.getInstance();
        c.setTime(timestamp);
        c.add(calendarField, amount);
        return new Timestamp(c.getTimeInMillis());
    }
    
    /**
     * <生成最小的当天日期值>
     * @return 最小的当天日期值
     */
    public static Timestamp nowSql() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return new Timestamp(c.getTimeInMillis());
    }

    /**
     * 获取当前时间
     * @return 当前时间
     */
    public static Date now() {
        Calendar c = Calendar.getInstance();
        return c.getTime();
    }
    
    public static String getDate() {
		return getDate(PATTERN_YMD);
	}
    
    public static String getDate(String format) {
		return getDate(format, Calendar.getInstance());
	}


	public static String getDate(Calendar cal) {
		return getDate(PATTERN_YMD, cal);
	}

	public static String getDate(Date date) {
		return getDate(PATTERN_YMD, date);
	}

	public static String getDate(String format, Calendar cal) {
        if (cal == null){
            cal = Calendar.getInstance();
        }
		return getDate(format, cal.getTime());
	}

	public static String getDate(String format, Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		if (date == null){
		    date = new Date();
        }
		return formatter.format(date);
	}

    /**
     * 获取日期差
     * @param fromDate 开始 (小)
     * @param toDate 结束 (大)
     * @return 格式化好的日期 (yyyy'Y'MM'M'dd'D'HH'H'mm'm'ss'S'SSS'MS')
     */
    public static String getTimeDifference(Date fromDate, Date toDate) {
        try{
            //获取时间差
            Calendar from = Calendar.getInstance();
            from.setTime(fromDate);
            Calendar to = Calendar.getInstance();
            to.setTime(toDate);

            int fromYear = from.get(Calendar.YEAR);
            int fromMonth = from.get(Calendar.MONTH);
            int fromDay = from.get(Calendar.DAY_OF_MONTH);
            int fromHour = from.get(Calendar.HOUR_OF_DAY);
            int fromMinute = from.get(Calendar.MINUTE);
            int fromSecond = from.get(Calendar.SECOND);
            int fromMilliSecond = from.get(Calendar.MILLISECOND);

            int toYear = to.get(Calendar.YEAR);
            int toMonth = to.get(Calendar.MONTH);
            int toDay = to.get(Calendar.DAY_OF_MONTH);
            int toHour = to.get(Calendar.HOUR_OF_DAY);
            int toMinute = to.get(Calendar.MINUTE);
            int toSecond = to.get(Calendar.SECOND);
            int toMilliSecond = to.get(Calendar.MILLISECOND);

            int year = toYear - fromYear;
            int month = toMonth - fromMonth;
            int day = toDay - fromDay;
            int hour = toHour - fromHour;
            int minute = toMinute - fromMinute;
            int second = toSecond - fromSecond;
            int milliSecond = toMilliSecond - fromMilliSecond;

            String format = "";

            Calendar diffTime = Calendar.getInstance();

            // 毫秒
            if (milliSecond != 0){
                diffTime.set(Calendar.MILLISECOND, milliSecond);
                format = "SSS'MS'";
            } else {
                format = "000'MS'";
            }

            // 秒
            if (second != 0){
                diffTime.set(Calendar.SECOND, second);
                format = "ss'S'" + format;
            } else {
                format = "00'S'" + format;
            }

            // 分
            if (minute != 0){
                diffTime.set(Calendar.MINUTE, minute);
                format = "mm'm'" + format;
            } else {
                format = "00'm'" + format;
            }

            // 小时
            if (hour != 0){
                diffTime.set(Calendar.HOUR_OF_DAY, hour);
                format = "HH'H'" + format;
            } else {
                format = "00'H'" + format;
            }

            // 日
            if (day != 0){
                diffTime.set(Calendar.DAY_OF_MONTH, day);
                format = "dd'D'" + format;
            } else {
                format = "00'D'" + format;
            }

            // 月
            if (month != 0){
                diffTime.set(Calendar.MONTH, month - 1);
                format = "MM'M'" + format;
            } else {
                format = "00'M'" + format;
            }

            // 年
            if (year != 0){
                diffTime.set(Calendar.YEAR, year);
                format = "yyyy'Y'" + format;
            } else {
                format = "0000'Y'" + format;
            }
            return formatDate(diffTime.getTime(), format);
        }catch(Exception e){
            System.out.println("计算错误");
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 判断是否润年
     *
     * @param date
     * @return
     */
    public static boolean isLeapYear(String date) {

        /**
         * 详细设计： 1.被400整除是闰年，否则： 2.不能被4整除则不是闰年 3.能被4整除同时不能被100整除则是闰年
         * 3.能被4整除同时能被100整除则不是闰年
         */
        Date d = parseDate(date);
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime(d);
        int year = gc.get(Calendar.YEAR);
        if ((year % 400) == 0){
            return true;
        } else if ((year % 4) == 0) {
            if ((year % 100) == 0){
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    
    
    /** This class should not be instantiated. */
    private CodCommonDate() {
    	
    }

    public static void main(String[] args) {
        String a = CodCommonDate.getTimeDifference(CodCommonDate.parseDate("2018-08-07 20:59:00", PATTERN_YMDHMS), new Date());
        System.out.println(a);
        Date date = parseDate(a, PATTERN_DIFF);
        System.out.println(formatDate(date, PATTERN_YMDHMS));
    }
}
