package com.hanggle.frames.exception;

import com.alibaba.fastjson.JSONObject;
import com.hanggle.frames.base.ErrorMsg;

/**
 * description: 自定义异常类
 * author: Smile
 * date: 2017/4/23
 */
public class ServiceException extends RuntimeException {

    private Integer code;

    public ServiceException(ServiceExceptionEmun serviceExceptionEmun){
        super(serviceExceptionEmun.getMessge());
        this.code = serviceExceptionEmun.getCode();
    }

    public ServiceException(ErrorMsg errorMsg){
        super(errorMsg.message());
        this.code = Integer.valueOf(errorMsg.code());
    }

    public ServiceException(String message){
        super(message);
        this.code = 600;
    }

    Integer getCode() {
        return code;
    }

    @Override
    public String toString() {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", this.getCode());
        jsonObject.put("message", this.getMessage());

        return jsonObject.toString();
    }
}
