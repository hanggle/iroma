package com.hanggle.base;

/**
 * description: 返回结果参数枚举类 <br/>
 * author: Smile <br/>
 * date: 2017/4/23 <br/>
 */
public enum ResultEmun {
    UNKNOWN_ERROR(500,"未知错误", ""),
    SUCCESS(200, "请求成功"),
    REQUEST_ERROR(404, "请求失败", "");

    private Integer status;
    private String message;
    private Object data;

    ResultEmun(Integer status, String message) {
        this.status = status;
        this.message = message;
    }

    ResultEmun(Integer status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public Integer getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }
}
