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

    private static final int STATUS_SUCCESS = 200;
    private static final int STATUS_FAIL = 400;
    private static final int STATUS_ERROR = 500;

    private Long timestamp=System.currentTimeMillis();
    private int status;
    private String code;
    private String message;
    private T data;

    public Response(){}

    public Response(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public Response(int status, String code, T data) {
        this.status = status;
        this.code = code;
        this.message = code;
        this.data = data;
    }

    public Response(T data) {
        this.status = STATUS_SUCCESS;
        this.code = ResponseStatus.SUCCESS.code();
        this.message = ResponseStatus.SUCCESS.message();
        this.data = data;
    }

    public Response(int status, T data) {
        this.status = status;
        this.code = ResponseStatus.SUCCESS.code();
        this.message = ResponseStatus.SUCCESS.message();
        this.data = data;
    }

    public Response(T data, String code) {
        this.status = STATUS_SUCCESS;
        this.code = code;
        this.data = data;
    }

    /**
     *  请求成功时返回
     * @return 返回体
     */
    public static Response<Boolean> success(){
        return new Response<>(STATUS_SUCCESS, true);
    }
    /**
     *  请求成功时返回
     * @param data 返回的结果
     * @return 返回体
     */
    public static<T> Response<T> success(T data){
        return new Response<>(data);
    }

    /**
     *  请求成功时返回
     * @param data 返回的结果
     * @return 返回体
     */
    public static<T> Response<T> success(T data, String code){
        return new Response<>(data, code);
    }
    /**
     *  请求成功，处理失败时返回
     * @param message 返回的错误信息
     * @return 返回体
     */
    public static<T> Response<T> fail(String message){
        return new Response<>(STATUS_FAIL, message);
    }

    /**
     *  请求成功，处理失败时返回
     * @param ResponseStatus 返回的错误信息
     * @return 返回体
     */
    public static<T> Response<T> fail(ResponseStatus ResponseStatus){
        return new Response<>(STATUS_FAIL, ResponseStatus.message());
    }

    /**
     *  请求成功，处理失败时返回
     * @param message 返回的错误信息
     * @return 返回体
     */
    public static<T> Response<T> fail(int status, String message){
        return new Response<>(status, message);
    }

    /**
     * 请求失败
     * @return 返回体
     */
    public static Response error(int status, String message){
        return new Response(status, message);
    }

    /**
     * 请求失败
     * @return 返回体
     */
    public static Response error(String code){
        return new Response(STATUS_FAIL, code);
    }
}
