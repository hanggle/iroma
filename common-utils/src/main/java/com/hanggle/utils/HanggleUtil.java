package com.hanggle.utils;

import com.google.common.hash.Hashing;

import java.util.Objects;
import java.util.UUID;

/**
 * Description:  自己的工具类
 * User: z.hang
 * Date: 2018-01-14
 * Time: 9:31
 */
public class HanggleUtil {

    /**
     * 32位大写UUID
     *
     * @author 11
     * @date 2018/1/14 9:56
     * @return java.lang.String
     */
    public static String getUUID(){
        return UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
    }

    /**
     * 32位大写UUID
     *
     * @author 11
     * @date 2018/1/14 9:56
     * @return java.lang.String
     */
    public static String  MD5(String src){
        if (Objects.isNull(src)) {
            return null;
        }
        return Hashing.md5().hashBytes(src.getBytes()).toString();
    }

}
