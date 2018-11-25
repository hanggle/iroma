package com.hanggle.frames.base;

import lombok.Data;

/**
 * description: 返回结果实体类
 * @author: Smile
 * date: 2017/4/23
 */
@Data
public class BaseResult<T> {

    public static final int CODE_SUCCESS = 20000;
    public static final int CODE_UNKNOWN_ERROR = 50000;
    public static final int CODE_REQUEST_ERROR = 40000;
    public static final String MESSAGE_FAIL = "请求失败！";
    public static final String MESSAGE_SUCCESS = "请求成功！";

    private Boolean success;
    private int code;
    private String message;
    private T data;

    public BaseResult(){}

    public BaseResult(Boolean success, int code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }

    public BaseResult(Boolean success, int code, String message, T data) {
        this.success = success;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public BaseResult(T data) {
        this.success = true;
        this.code = BaseResult.CODE_SUCCESS;
        this.message = BaseResult.MESSAGE_SUCCESS;
        this.data = data;
    }
    public BaseResult(T data, String message) {
        this.success = true;
        this.code = BaseResult.CODE_SUCCESS;
        this.message = message;
        this.data = data;
    }

    public BaseResult<T> setSuccess(Boolean success) {
        this.success = success;
        return this;
    }

    public BaseResult<T> setCode(int code) {
        this.code = code;
        return this;
    }

    public BaseResult<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    public BaseResult<T> setData(T data) {
        this.data = data;
        return this;
    }
}
