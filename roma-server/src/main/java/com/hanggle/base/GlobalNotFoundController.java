package com.hanggle.base;

import com.alibaba.fastjson.JSONObject;
import com.hanggle.util.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * description: 404 异常<br/>
 * author: zh <br/>
 * date: 2018/4/9 <br/>
 */
@Controller
public class GlobalNotFoundController implements ErrorController{

    private static Logger logger = LoggerFactory.getLogger(GlobalNotFoundController.class);

    @Override
    public String getErrorPath() {

        logger.info("404");
        JSONObject jobj = new JSONObject();
        jobj.put("404", "qwqer");

        return jobj.toString();
    }

    @RequestMapping(value="/error")
    @ResponseBody
    public Object handleError(){

        return ResultUtil.requestError();
    }
}
