package com.hanggle.frames.base;

import lombok.Data;

import java.io.Serializable;

/**
 * description: 返回结果工具类
 * @author: Smile
 * date: 2017/4/23
 */
@Data
public class Response<T> implements Serializable {

    private static final long serialVersionUID = -1277927133193484182L;

    private boolean success;
    private int code;
    private String message;
    private T data;

    public Response(){}

    public Response(Boolean success, int code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }

    public Response(Boolean success, int code, String message, T data) {
        this.success = success;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Response(T data) {
        this.success = true;
        this.code = ResponseCode.SUCCESS.code();
        this.message = ResponseCode.SUCCESS.message();
        this.data = data;
    }
    public Response(T data, String message) {
        this.success = true;
        this.code = ResponseCode.SUCCESS.code();
        this.message = message;
        this.data = data;
    }

    /**
     *  请求成功时返回
     * @return {"code":2000,"data":"true","message":"请求成功！"}
     */
    public static Response<Boolean> success(){
        return new Response<>(true);
    }
    /**
     *  请求成功时返回
     * @param data 返回的结果
     * @return {"code":2000,"data":data,"message":"请求成功！"}
     */
    public static<T> Response<T> success(T data){
        return new Response<>(data);
    }

    /**
     *  请求成功时返回
     * @param data 返回的结果
     * @return {"code":2000,"data":"","message":"请求成功！"}
     */
    public static<T> Response<T> success(T data, String message){
        return new Response<>(data, message);
    }
    /**
     *  请求成功，处理失败时返回
     * @param message 返回的错误信息
     * @return {"code":2000,"data":"","message":""}
     */
    public static<T> Response<T> fail(String message){
        return new Response<>(false, ResponseCode.FAIL.code(), message);
    }

    /**
     *  请求成功，处理失败时返回
     * @param message 返回的错误信息
     * @return {"code":2000,"data":"","message":""}
     */
    public static<T> Response<T> fail(int code, String message){
        return new Response<>(false, code, message);
    }

    /**
     * 请求失败
     * @return {"code":5000,"message":"请求失败！"}
     */
    public static Response error(int code, String message){
        return new Response(false, code, message);
    }
}
