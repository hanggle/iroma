package com.hanggle.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
    public static final String FORMAT_YYYY_MM_DD_HH_MM_SS= "yyyy-MM-dd HH:mm:ss";
    /**
     * yyyy-MM-dd HH:mm 格式
     */
    public static final String FORMAT_YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";
    /**
     * yyyy-MM-dd HH 格式
     */
    public static final String FORMAT_YYYY_MM_DD_HH = "yyyy-MM-dd HH";
    /**
     * yyyy-MM-dd 格式
     */
    public static final String FORMAT_YYYY_MM_DD = "yyyy-MM-dd";
    /**
     * HH:mm:ss 格式
     */
    public static final String FORMAT_HH_MM_SS = "HH:mm:ss";
    /**
     * HH:mm 格式
     */
    public static final String FORMAT_HH_MM = "HH:mm";


    public static String getDate(){
        return  LocalDate.now().toString();
    }
    public static String getDate(int year){
        return  LocalDate.now().toString();
    }
    public static String getDate(int year, int month){
        return  LocalDate.now().toString();
    }
    public static String getDate(int year, int month, int day){
        return  LocalDate.now().toString();
    }


    /** sssss
     * @author zh
     * @date 2018/1/14 12:20
     * @return java.lang.String
     */
    public static String getDateTime(){
        return  LocalDateTime.now().toString();
    }

}
