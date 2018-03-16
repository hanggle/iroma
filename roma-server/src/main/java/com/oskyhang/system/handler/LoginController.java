package com.oskyhang.system.handler;

import com.alibaba.fastjson.JSONObject;
import com.microhang.base.BaseController;
import com.oskyhang.system.service.BdMenuService;
import org.springframework.beans.factory.annotation.Autowired;
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
public class LoginController extends BaseController {

    @Autowired
    private BdMenuService bdMenuService;

    @RequestMapping("/login/login")
    @ResponseBody
    public String testExc(HttpServletRequest request, HttpServletResponse response){
        JSONObject obj = new JSONObject();
        obj.put("code", "1");
        obj.put("desc", "sss");
        return obj.toString();
    }

    @RequestMapping("/login/logout")
    @ResponseBody
    public String logout(HttpServletRequest request, HttpServletResponse response){
        JSONObject obj = new JSONObject();
        obj.put("code", "1");
        obj.put("desc", "sss");
        return obj.toString();
    }

}

