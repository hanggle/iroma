package com.common.base;

/**
 * description: 自定义异常类
 * author: Smile
 * date: 2017/4/23
 */
public class MyException extends RuntimeException {

    private Integer code;
    public MyException(ResultEmun resultEmun){
        super(resultEmun.getMsg());
        this.code = resultEmun.getCode();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
