package com.oskyhang.system.handler;

import com.alibaba.fastjson.JSONObject;
import com.hanggle.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * description: 用户登录模块<br/>
 * author: zh <br/>
 * date: 2018/3/12 <br/>
 */
@Controller
public class BdUserController extends BaseController {

    @RequestMapping("/user/info")
    @ResponseBody
    public String test(HttpServletRequest request, HttpServletResponse response){
        JSONObject obj = new JSONObject();
        String [] roles = {"/documentation", "index",  "/permission", "admin"};
        obj.put("roles", roles);
        obj.put("name", "tom");
        obj.put("username", "admin");
        obj.put("avatar", "qqq");
        obj.put("introduction", "this is introduction");
        return obj.toString();
    }

}

