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
public class CommonUtil {

    /**
     * 32位大写UUID
     *
     * @author 11
     * @date 2018/1/14 9:56
     * @return java.lang.String
     */
    public static String UUID(){
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 32位大写UUID
     *
     * @return java.lang.String
     * @author 11
     * @date 2018/1/14 9:56
     */
    public static String MD5(String src) {
        if (Objects.isNull(src)) {
            throw new NullPointerException();
        }

        return Hashing.md5().hashBytes(src.getBytes()).toString();
    }

}
