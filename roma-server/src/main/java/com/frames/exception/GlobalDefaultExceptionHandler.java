package com.frames.exception;

import com.alibaba.fastjson.JSONObject;
import com.frames.base.BaseResult;
import com.frames.util.ResultUtil;
import com.google.common.base.Throwables;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Description: 全局异常 500 <br/>
 * User: z.hang <br/>
 * Date: 2018-01-16 <br/>
 * Time: 0:03 <br/>
 */
@Slf4j
@ControllerAdvice
public class GlobalDefaultExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Object defaultErrorHandler(HttpServletRequest req, Exception e)  {

        BaseResult baseResult = null;
        //业务异常
        if(e instanceof ServiceException){
            baseResult = new BaseResult(e);
            return JSONObject.toJSONString(baseResult);
        }

        if(e instanceof HttpRequestMethodNotSupportedException){
            baseResult = new BaseResult(BaseResult.CODE_REQUEST_ERROR, e.getMessage());
            return JSONObject.parse(baseResult.toString());
        }
        baseResult = ResultUtil.unknowError();
        log.info(baseResult.toString(), Throwables.getStackTraceAsString(e));
        return JSONObject.parse(baseResult.toString());
    }
}
