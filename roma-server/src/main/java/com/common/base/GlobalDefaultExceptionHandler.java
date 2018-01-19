package com.common.base;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * Description: 全局异常 <br/>
 * User: z.hang <br/>
 * Date: 2018-01-16 <br/>
 * Time: 0:03 <br/>
 */
@ControllerAdvice
public class GlobalDefaultExceptionHandler {

    private static Logger logger = LoggerFactory.getLogger(GlobalDefaultExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    public String defaultErrorHandler(HttpServletRequest req, Exception e)  {
        logger.debug("2222" ,e);
        JSONObject obj = new JSONObject();
        obj.put("!", "22222");
        return obj.toString();
    }
}
