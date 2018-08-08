package com.frames.util;

import com.frames.base.Result;

/**
 * description: 结果集返回工具类 <br/>
 * author: zh <br/>
 * date: 2018/4/10 <br/>
 */
public class ResultUtil {

    /**
     *  请求成功时返回
     * @param data 返回的结果
     * @return {"code":2000,"data":"","desc":"请求成功！"}
     */
    public static Result success(Object data){
        return new Result(data);
    }

    /**
     * 请求失败
     * @return {"code":4000,"desc":"请求失败！"}
     */
    public static Result error(){
        Result result = new Result();
        result.setCode(Result.CODE_REQUEST_ERROR);
        result.setDesc(Result.DESC_FAIL);
        return result;
    }

    public static Result requestError(){
        Result result = new Result();
        result.setCode(Result.CODE_REQUEST_ERROR);
        result.setDesc(Result.DESC_FAIL);
        return result;
    }

    public static Result unknowError(){
        Result result = new Result();
        result.setCode(Result.CODE_UNKNOWN_ERROR);
        result.setDesc(Result.DESC_FAIL);
        return result;
    }
}
