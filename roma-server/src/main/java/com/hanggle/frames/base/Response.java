package com.hanggle.frames.base;

import lombok.Data;
import sun.security.provider.certpath.OCSPResponse;

import java.io.Serializable;

/**
 * description: 返回结果工具类
 * @author: Smile
 * date: 2017/4/23
 */
@Data
public class Response<T> implements Serializable {

    private static final long serialVersionUID = -1277927133193484182L;

    private Long timestamp=System.currentTimeMillis();
    private int status;
    private String message;
    private T data;

    public Response(){}

    public Response(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public Response(int status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public Response(T data) {
        this.status = ResponseStatus.SUCCESS.status();
        this.message = ResponseStatus.SUCCESS.message();
        this.data = data;
    }
    public Response(T data, String message) {
        this.status = ResponseStatus.SUCCESS.status();
        this.message = message;
        this.data = data;
    }

    /**
     *  请求成功时返回
     * @return {"status":2000,"data":"true","message":"请求成功！"}
     */
    public static Response<Boolean> success(){
        return new Response<>(true);
    }
    /**
     *  请求成功时返回
     * @param data 返回的结果
     * @return {"status":2000,"data":data,"message":"请求成功！"}
     */
    public static<T> Response<T> success(T data){
        return new Response<>(data);
    }

    /**
     *  请求成功时返回
     * @param data 返回的结果
     * @return {"status":2000,"data":"","message":"请求成功！"}
     */
    public static<T> Response<T> success(T data, String message){
        return new Response<>(data, message);
    }
    /**
     *  请求成功，处理失败时返回
     * @param message 返回的错误信息
     * @return {"status":600*,"data":"","message":""}
     */
    public static<T> Response<T> fail(String message){
        return new Response<>(ResponseStatus.FAIL.status(), message);
    }

    /**
     *  请求成功，处理失败时返回
     * @param ResponseStatus 返回的错误信息
     * @return {"status":600*,"data":"","message":""}
     */
    public static<T> Response<T> fail(ResponseStatus ResponseStatus){
        return new Response<>(ResponseStatus.status(), ResponseStatus.message());
    }

    /**
     *  请求成功，处理失败时返回
     * @param message 返回的错误信息
     * @return {"status":600*,"data":"","message":""}
     */
    public static<T> Response<T> fail(int status, String message){
        return new Response<>(status, message);
    }

    /**
     * 请求失败
     * @return {"status":5000,"message":"请求失败！"}
     */
    public static Response error(int status, String message){
        return new Response(status, message);
    }
}
