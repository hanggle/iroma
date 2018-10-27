package com.frames.base;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * description: 返回结果实体类
 * author: Smile
 * date: 2017/4/23
 */
public class BaseResult<T> {

    public static final int CODE_SUCCESS = 2000;
    public static final int CODE_UNKNOWN_ERROR = 5000;
    public static final int CODE_REQUEST_ERROR = 4000;
    public static final String DESC_FAIL = "请求失败！";
    public static final String DESC_SUCCESS = "请求成功！";

    private int code;
    private String desc;
    private T data;

    public BaseResult(){}

    public BaseResult(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public BaseResult(int code, String desc, T data) {
        this.code = code;
        this.desc = desc;
        this.data = data;
    }

    public BaseResult(T data) {
        this.code = BaseResult.CODE_SUCCESS;
        this.desc = BaseResult.DESC_SUCCESS;
        this.data = data;
    }

    @Override
    public String toString() {
        JSONObject obj = new JSONObject();
        obj.put("code", this.code);
        obj.put("desc", this.desc);
        obj.put("data", JSON.toJSON(this.data));
        return obj.toString();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
