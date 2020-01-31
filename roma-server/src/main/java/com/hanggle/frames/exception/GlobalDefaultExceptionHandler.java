package com.hanggle.frames.exception;

import com.hanggle.frames.base.Response;
import com.google.common.base.Throwables;
import com.hanggle.frames.base.ResponseStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Description: 全局异常 500 <br/>
 * @author : z.hang <br/>
 * Date: 2018-01-16 <br/>
 * Time: 0:03 <br/>
 */
@Slf4j
@ControllerAdvice
public class GlobalDefaultExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Response defaultErrorHandler(HttpServletRequest req, Exception e)  {
        if(e instanceof HttpRequestMethodNotSupportedException){
            log.error("GlobalDefaultExceptionHandler[]HttpRequestMethodNotSupportedException,case:{}", Throwables.getStackTraceAsString(e));
            return Response.error(ResponseStatus.REQUEST_ERROR.code());
        }
        log.error("GlobalDefaultExceptionHandler[]defaultErrorHandler:{}", Throwables.getStackTraceAsString(e));
        return Response.error(ResponseStatus.UNKNOWN_ERROR.code());
    }

    /**
     * 业务异常
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = ServiceException.class)
    @ResponseBody
    public Response serviceExceptionHandler(HttpServletRequest req, Exception e)  {
        return Response.error(ResponseStatus.FAIL.code());
    }

    @GetMapping(value = "/error/404")
    public Response error400() {
        log.error("GlobalDefaultExceptionHandler[]defaultErrorHandler:{}", 123444);
        return Response.error(ResponseStatus.UNKNOWN_ERROR.code());
    }

    @GetMapping(value = "/error/500")
    public Response error500() {
        log.error("GlobalDefaultExceptionHandler[]defaultErrorHandler:{}", 123444);
        return Response.error(ResponseStatus.UNKNOWN_ERROR.code());
    }



}
