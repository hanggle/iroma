package com.hanggle.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Description: 日期工具类
 * User: z.hang <br/>
 * Date: 2018-01-10 <br/>
 * Time: 22:04 <br/>
 */
public class DateUtil {

    public static String yyyy_MM_dd_HH_mm_ss ="S";

    /**
     * yyyy-MM-dd HH:mm:ss 格式
     */
    public static final String YYYY_MM_DD_HH_MM_SS= "yyyy-MM-dd HH:mm:ss";
    /**
     * yyyy-MM-dd HH:mm 格式
     */
    public static final String YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";
    /**
     * yyyy-MM-dd HH 格式
     */
    public static final String YYYY_MM_DD_HH = "yyyy-MM-dd HH";
    /**
     * yyyy-MM-dd 格式
     */
    public static final String YYYY_MM_DD = "yyyy-MM-dd";
    /**
     * HH:mm:ss 格式
     */
    public static final String HH_MM_SS = "HH:mm:ss";
    /**
     * HH:mm 格式
     */
    public static final String HH_MM = "HH:mm";


    public static Integer getYear(){
        return  getYear(LocalDateTime.now(), 0);
    }

    public static Integer getYear(LocalDateTime localDateTime, int year){
        return  getDateTimeYear(localDateTime, year).getYear();
    }
    public static Integer getYearAndMonth(){
        return  getYearAndMonth(LocalDateTime.now());
    }

    public static Integer getYearAndMonth(LocalDateTime localDateTime){
        int year = localDateTime.getYear();
        int month = localDateTime.getMonthValue();
        return  Integer.valueOf(Integer.toString(year) + Integer.toString(month));
    }
    /**
     *  当前时间加减年
     * @param year 加减的年数
     * @return
     */
    public static LocalDateTime getDateTimeYear(int year){
        return  getDateTimeYear(LocalDateTime.now(), year);
    }

    /**
     * 时间加减年
     * @param localDateTime 日期
     * @param year 加减的年数
     * @return
     */
    public static LocalDateTime getDateTimeYear(LocalDateTime localDateTime, int year){
        return  localDateTime.plusYears(year);
    }

    /**
     *  当前时间加减月
     * @param month 加减的年数
     * @return
     */
    public static LocalDateTime getDateTimeMonth(int month){
        return  getDateTime(LocalDateTime.now(), month);
    }

    /**
     * 时间加减年
     * @param localDateTime 日期
     * @param year 加减的年数
     * @return
     */
    public static LocalDateTime getDateTime(LocalDateTime localDateTime, int year){
        return  localDateTime.plusYears(year);
    }

    public static String getDate(int year, int month){
        return  LocalDate.now().toString();
    }
    public static String getDate(int year, int month, int day){
        return  LocalDate.now().toString();
    }

    public static String getDateTime(String format){
        return getDateTime(LocalDateTime.now(), format);
    }
    public static String getDateTime(){
        return getDateTime(LocalDateTime.now(), YYYY_MM_DD_HH_MM_SS);
    }

    /**
     * 获取零点零分零秒
     * @return 时间
     */
    public static LocalDateTime getDateTime000000(){
        return getDateTime000000(LocalDateTime.now());
    }
    public static LocalDateTime getDateTime000000(LocalDateTime localDateTime){
        return localDateTime.withHour(0).withMinute(0).withSecond(0).withNano(0);
    }

    public static String getDateTime(LocalDateTime localDateTime, String format){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(format);
        return localDateTime.format(dateTimeFormatter);
    }

    /**
     * 获取零点零分零秒
     * @return 时间
     */
    public static String getDate(){
        return getDate(LocalDate.now(), YYYY_MM_DD);
    }
    public static String getDate(LocalDate localDate, String format){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(format);
        return localDate.format(dateTimeFormatter);
    }


    public static void main(String[] args){
        System.out.println("10000000000000000001".length());
        System.out.println("9223372036854775807".length());
        System.out.println(getDate());
        System.out.println(getDateTime(YYYY_MM_DD_HH_MM_SS));
        System.out.println(getDateTime(getDateTime000000(), YYYY_MM_DD_HH_MM_SS));
        System.out.println(getYearAndMonth());
    }

}
