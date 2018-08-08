package com.frames.exception;

import com.alibaba.fastjson.JSONObject;
import com.frames.util.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * description: 404 异常<br/>
 * author: zh <br/>
 * date: 2018/4/9 <br/>
 */

@Controller
@RequestMapping("/error")
public class GlobalNotFoundController{

    private static Logger logger = LoggerFactory.getLogger(GlobalNotFoundController.class);


    @RequestMapping(value="error")
    @ResponseBody
    public Object handleError(){
        String str =  ResultUtil.requestError().toString();
        return JSONObject.toJSONString(ResultUtil.requestError());
    }
}
