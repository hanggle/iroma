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
     * @return {"code":2000,"data":"","desc":"请求成功！"}
     */
    public static BaseResult success(Object data){
        return new BaseResult(data);
    }

    /**
     * 请求失败
     * @return {"code":4000,"desc":"请求失败！"}
     */
    public static BaseResult error(){
        BaseResult baseResult = new BaseResult();
        baseResult.setCode(BaseResult.CODE_REQUEST_ERROR);
        baseResult.setDesc(BaseResult.DESC_FAIL);
        return baseResult;
    }

    public static BaseResult requestError(){
        BaseResult baseResult = new BaseResult();
        baseResult.setCode(BaseResult.CODE_REQUEST_ERROR);
        baseResult.setDesc(BaseResult.DESC_FAIL);
        return baseResult;
    }

    public static BaseResult unknowError(){
        BaseResult baseResult = new BaseResult();
        baseResult.setCode(BaseResult.CODE_UNKNOWN_ERROR);
        baseResult.setDesc(BaseResult.DESC_FAIL);
        return baseResult;
    }
}
