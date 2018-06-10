package com.oskyhang.system.controller;

import com.alibaba.fastjson.JSONObject;
import com.hanggle.base.BaseController;
import com.oskyhang.system.service.BdMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * description: 用户登录模块<br/>
 * author: zh <br/>
 * date: 2018/3/12 <br/>
 */
@RestController
@Api(value = "sss", tags = "登录")
@RequestMapping("/login")
public class LoginController extends BaseController {

    @Autowired
    private BdMenuService bdMenuService;

    @ApiOperation(value = "dasfasdf", notes = "sdsdafasdf")
    @RequestMapping(value = "/login")
    public String login(HttpServletRequest request, String username, String password){
        JSONObject obj = new JSONObject();
        String datas = request.getParameter("data2");
        obj.put("code", "1o");
        obj.put("desc", "sss");
        obj.put("token", "12wqs");
        return obj.toString();
    }

    @ApiOperation(value = "dasfasdf", notes = "sdsdafasdf")
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public String logout(HttpServletRequest request, HttpServletResponse response){
        JSONObject obj = new JSONObject();
        obj.put("code", "1");
        obj.put("desc", "sss");
        return obj.toString();
    }

}

