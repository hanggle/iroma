package com.hanggle.config;

/**
 * description: 自定义异常类
 * author: Smile
 * date: 2017/4/23
 */
public enum ServiceExceptionEmun {
    NUMER_TOO_BIG("数字太大"),
    NUMER_TOO_BIG_CODE(1, "数字太大");

    ServiceExceptionEmun(String messge) {
        this.messge = messge;
    }

    ServiceExceptionEmun(int code, String messge) {
        this.code = code;
        this.messge = messge;
    }

    private int code;
    private String messge;

    public int getCode() {
        return code;
    }

    public String getMessge() {
        return messge;
    }
}
