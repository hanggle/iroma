package com.hanggle.base;

/**
 * description: 返回结果参数枚举类
 * author: Smile
 * date: 2017/4/23
 */
public enum ResultEmun {
    UNKNOWN_ERROR(0,"未知错误"),
    SUCCESS(1,"请求成功");

    private Integer code;
    private String msg;

    ResultEmun(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}
