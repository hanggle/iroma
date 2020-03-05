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

    private static final int CODE_SUCCESS = 200;
    private static final int CODE_FAIL = 400;
    private static final int CODE_ERROR = 500;

    private Long timestamp=System.currentTimeMillis();
    private int code;
    private String message;
    private T data;

    public Response(){}

    public Response(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public Response(int code, String msg, T data) {
        this.code = code;
        this.message = msg;
        this.data = data;
    }

    public Response(T data) {
        this.code = CODE_SUCCESS;
        this.message = ErrorMsg.SUCCESS.message();
        this.data = data;
    }

    public Response(int code, T data) {
        this.code = code;
        this.message = ErrorMsg.SUCCESS.message();
        this.data = data;
    }

    public Response(T data, String code) {
        this.code = CODE_SUCCESS;
        this.data = data;
    }

    /**
     *  请求成功时返回
     * @return 返回体
     */
    public static Response<Boolean> success(){
        return new Response<>(CODE_SUCCESS, true);
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
        return new Response<>(CODE_FAIL, message);
    }

    /**
     *  请求成功，处理失败时返回
     * @param ErrorMsg 返回的错误信息
     * @return 返回体
     */
    public static<T> Response<T> fail(ErrorMsg ErrorMsg){
        return new Response<>(CODE_FAIL, ErrorMsg.message());
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
        return new Response(CODE_FAIL, code);
    }
}
