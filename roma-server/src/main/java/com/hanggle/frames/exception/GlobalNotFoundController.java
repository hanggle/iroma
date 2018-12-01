package com.hanggle.frames.exception;

import com.hanggle.frames.base.Response;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * description: 404 异常<br/>
 * @author: zh <br/>
 * date: 2018/4/9 <br/>
 */

@Controller
@RequestMapping("/error")
public class GlobalNotFoundController{

    @RequestMapping(value="error")
    @ResponseBody
    public Object handleError(){
        return Response.error(Response.CODE_REQUEST_ERROR, Response.MESSAGE_FAIL);
    }
}
