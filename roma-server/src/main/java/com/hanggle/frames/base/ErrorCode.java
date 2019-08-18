package com.hanggle.frames.base;

/**
 * @description:
 * @author: hanggle
 * @date: 2018/11/29
 */
public enum ErrorCode{
    CREATE_FAIL("CREATE_FAIL", "创建失败"),
    UPDATE_FAIL("UPDATE_FAIL", "更新失败"),
    DELETE_FAIL("DELETE_FAIL", "删除失败"),
    QUERY_FAIL("QUERY_FAIL", "查询失败"),
            ;
    private String code;
    private String message;

    public String code(){
        return this.code;
    }

    public String message(){
        return this.message;
    }

    ErrorCode(String code, String message){
        this.code = code;
        this.message = message;
    }
}
