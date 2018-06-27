package com.frames.util;

import com.frames.base.Result;
import com.frames.base.ResultEmun;

/**
 * description: 结果集返回工具类 <br/>
 * author: zh <br/>
 * date: 2018/4/10 <br/>
 */
public class ResultUtil {

    public static Result success(Object data){
        return new Result(data);
    }

    public static Result requestError(){
       return new Result(ResultEmun.REQUEST_ERROR);
    }
    public static Result unknowError(){
        return new Result(ResultEmun.UNKNOWN_ERROR);
    }
}
