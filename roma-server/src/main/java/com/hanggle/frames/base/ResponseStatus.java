package com.hanggle.frames.base;

/**
 * @description:
 * @author: hanggle
 * @date: 2018/11/29
 */
public enum ResponseStatus {
    SUCCESS(200, ""),
    UNKNOWN_ERROR(50000, "内部错误！"),
    REQUEST_ERROR(40000, "请求失败！"),
    FAIL(60000, "请求失败！"),
    FAIL_NOTLOGIN(60001, "用户未登录!"),
    ACCOUNT_AUTH_ERROR(60002, "用户名或密码错误!"),
    ACCOUNT_FREEZE_ERROR(60003, "账户被冻结!")
    ;
    private int status;
    private String message;

    public int status(){
        return this.status;
    }

    public String message(){
        return this.message;
    }

    ResponseStatus(int status, String message){
        this.status =status;
        this.message = message;
    }
}
