package com.frames.exception;

import com.alibaba.fastjson.JSONObject;
import com.frames.base.Result;
import com.frames.util.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Description: 全局异常 500 <br/>
 * User: z.hang <br/>
 * Date: 2018-01-16 <br/>
 * Time: 0:03 <br/>
 */
@ControllerAdvice
public class GlobalDefaultExceptionHandler {

    private static Logger logger = LoggerFactory.getLogger(GlobalDefaultExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Object defaultErrorHandler(HttpServletRequest req, Exception e)  {

        Object result = "";
        logger.debug("请求失败", e);
        //业务异常
        if(e instanceof ServiceException){
            result = new Result(e);
            return JSONObject.toJSONString(result);
        }

        if(e instanceof HttpRequestMethodNotSupportedException){
            result = new Result(Result.CODE_REQUEST_ERROR, e.getMessage());
            return JSONObject.parse(result.toString());
        }
        result = ResultUtil.unknowError();
        logger.debug(result.toString(), e);
        return JSONObject.parse(result.toString());
    }
}
