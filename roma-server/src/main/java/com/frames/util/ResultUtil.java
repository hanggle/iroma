package com.frames.util;

import com.frames.base.BaseResult;

/**
 * description: 结果集返回工具类 <br/>
 * author: zh <br/>
 * date: 2018/4/10 <br/>
 */
public class ResultUtil {

    /**
     *  请求成功时返回
     * @param data 返回的结果
     * @return {"code":2000,"data":"","message":"请求成功！"}
     */
    public static BaseResult success(Object data){
        return new BaseResult(data);
    }

    /**
     *  请求成功时返回
     * @param data 返回的结果
     * @return {"code":2000,"data":"","message":"请求成功！"}
     */
    public static BaseResult success(Object data, String message){
        BaseResult baseResult = new BaseResult(data, message);
        return new BaseResult(data, message);
    }

    /**
     * 请求失败
     * @return {"code":5000,"message":"请求失败！"}
     */
    public static BaseResult error(){
        return new BaseResult(false, BaseResult.CODE_UNKNOWN_ERROR, BaseResult.MESSAGE_FAIL);
    }
    /**
     * 用于捕获的异常 内部错误
     * @return {"code":5000,"message":"请求失败！"}
     */
    public static BaseResult error(Exception e){
        BaseResult baseResult = new BaseResult();
        baseResult.setSuccess(false)
                .setCode(BaseResult.CODE_UNKNOWN_ERROR)
                .setMessage(e.getMessage());
        return baseResult;
    }

    public static BaseResult requestError(){
        BaseResult baseResult = new BaseResult();
        baseResult.setSuccess(false)
                .setCode(BaseResult.CODE_REQUEST_ERROR)
                .setMessage(BaseResult.MESSAGE_FAIL);
        return baseResult;
    }

    public static BaseResult requestError(Exception e){
        BaseResult baseResult = new BaseResult();
        baseResult.setSuccess(false)
                .setCode(BaseResult.CODE_REQUEST_ERROR )
                .setMessage(e.getMessage());
        return baseResult;
    }

    public static BaseResult unknowError(Exception e){
        BaseResult baseResult = new BaseResult();
        baseResult.setSuccess(false)
                .setCode(BaseResult.CODE_UNKNOWN_ERROR)
                .setMessage(e.getMessage());
        return baseResult;
    }
}
