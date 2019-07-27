package com.hanggle.frames.exception;

import com.hanggle.frames.base.Response;
import com.hanggle.frames.base.ResponseStatus;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.charts.DataSources;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
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

    @RequestMapping(value="/error")
    @ResponseBody
    public Object handleError(){
        log.error("GlobalNotFoundController[]handleError");
        return Response.error(ResponseStatus.REQUEST_ERROR.status(), ResponseStatus.REQUEST_ERROR.message());
    }
}
