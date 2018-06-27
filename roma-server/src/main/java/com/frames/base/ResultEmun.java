package com.frames.base;

/**
 * description: 返回结果参数枚举类 <br/>
 * author: Smile <br/>
 * date: 2017/4/23 <br/>
 */
public enum ResultEmun {
    UNKNOWN_ERROR(500,"未知错误", ""),
    SUCCESS(200, "请求成功"),
    REQUEST_ERROR(400, "请求失败", "");

    private Integer code;
    private String desc;
    private Object data;

    ResultEmun(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    ResultEmun(Integer code, String desc, Object data) {
        this.code = code;
        this.desc = desc;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public Object getData() {
        return data;
    }
}
