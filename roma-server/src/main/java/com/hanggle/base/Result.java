package com.hanggle.base;

import com.alibaba.fastjson.JSONObject;

/**
 * description: 返回结果实体类
 * author: Smile
 * date: 2017/4/23
 */
public class Result {
    private int code;
    private String desc;
    private Object data;

    public Result(ResultEmun resultEmun, Object data) {
        this.code = resultEmun.getCode();
        this.desc = resultEmun.getDesc();
        this.data = data;
    }

    public Result(ResultEmun resultEmun) {
        this.code = resultEmun.getCode();
        this.desc = resultEmun.getDesc();
        this.data = resultEmun.getData();
    }

    public Result(Object data) {
        this.code = ResultEmun.SUCCESS.getCode();
        this.desc = ResultEmun.SUCCESS.getDesc();
        this.data = JSONObject.toJSONString(data);
    }

    public Result(Exception serviceException) {
        this.code = ResultEmun.SUCCESS.getCode();
        this.desc = ResultEmun.SUCCESS.getDesc();
        this.data = serviceException.toString();
    }

    @Override
    public String toString() {
        return "Result{" +
                "code='" + this.code + '\'' +
                ", desc='" + this.desc + '\'' +
                ", data=" + this.data +
                '}';
    }
}
