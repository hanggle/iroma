package com.hanggle.frames.base;

/**
 * @description:
 * @author: hanggle
 * @date: 2018/11/29
 */
public enum ErrorMsg {
    SUCCESS("20000", "请求成功"),
    UNKNOWN_ERROR("50000", "内部错误！"),
    REQUEST_ERROR("40000", "请求失败！"),
    FAIL("60000", "请求失败！"),
    FAIL_NOT_LOGIN("60001", "用户未登录!"),
    ACCOUNT_AUTH_ERROR("60002", "用户名或密码错误!"),
    ACCOUNT_FREEZE_ERROR("60003", "账户被冻结!"),
    ;
    private String code;
    private String message;

    public String code(){
        return this.code;
    }

    public String message(){
        return this.message;
    }

    ErrorMsg(String code, String message){
        this.code = code;
        this.message = message;
    }
}
