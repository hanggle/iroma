package com.hanggle.frames.base;

/**
 * @description:
 * @author: hanggle
 * @date: 2018/11/29
 */
public enum ResponseCode {
        SUCCESS(20000, "请求失败！"),
        UNKNOWN_ERROR(50000, "请求失败！"),
        REQUEST_ERROR(40000, "请求失败！"),
        FAIL(60000, "请求失败！"),
        FAIL_NOTLOGIN(60001, "用户未登录!")
        ;

        private int code;
        private String message;

        public int code(){
            return this.code;
        }

        public String message(){
            return this.message;
        }

        ResponseCode(int code, String message){
            this.code =code;
            this.message = message;
        }
}
