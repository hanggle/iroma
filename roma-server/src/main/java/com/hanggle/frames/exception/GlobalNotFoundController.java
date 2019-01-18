package com.hanggle.frames.exception;

import com.hanggle.frames.base.Response;
import com.hanggle.frames.base.ResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * description: 404 异常<br/>
 * @author: zh <br/>
 * date: 2018/4/9 <br/>
 */

@Slf4j
@Controller
@RequestMapping("/error")
public class GlobalNotFoundController{

    @RequestMapping(value="error")
    @ResponseBody
    public Object handleError(){
        log.error("GlobalNotFoundController[]handleError");
        return Response.error(ResponseCode.REQUEST_ERROR.code(), ResponseCode.REQUEST_ERROR.message());
    }
}
