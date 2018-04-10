package com.hanggle.base;

import com.alibaba.fastjson.JSONObject;

/**
 * description: 返回结果实体类
 * author: Smile
 * date: 2017/4/23
 */
public class Result {
    private int status;
    private String message;
    private Object data;

    public Result(ResultEmun resultEmun, Object data) {
        this.status = resultEmun.getStatus();
        this.message = resultEmun.getMessage();
        this.data = data;
    }

    public Result(ResultEmun resultEmun) {
        this.status = resultEmun.getStatus();
        this.message = resultEmun.getMessage();
        this.data = resultEmun.getData();
    }

    public Result(Object data) {
        this.status = ResultEmun.SUCCESS.getStatus();
        this.message = ResultEmun.SUCCESS.getMessage();
        this.data = JSONObject.toJSONString(data);
    }

    public Result(Exception serviceException) {
        this.status = ResultEmun.SUCCESS.getStatus();
        this.message = ResultEmun.SUCCESS.getMessage();
        this.data = serviceException.toString();
    }

    @Override
    public String toString() {
        return "Result{" +
                "status='" + this.status + '\'' +
                ", message='" + this.message + '\'' +
                ", data=" + this.data +
                '}';
    }
}
