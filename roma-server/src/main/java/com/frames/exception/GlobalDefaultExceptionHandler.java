package com.frames.exception;

import com.frames.base.Result;
import com.frames.util.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

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
    public Object defaultErrorHandler(HttpServletRequest req, Exception e)  {

        Object result = "";

        //业务异常
        if(e instanceof ServiceException){
            result = new Result(e);
            logger.debug(result.toString(), e);
            return result;
        }

        result = ResultUtil.unknowError();
        logger.debug(result.toString(), e);
        return result;
    }
}
