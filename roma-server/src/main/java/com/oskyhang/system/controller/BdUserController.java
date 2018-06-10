package com.oskyhang.system.controller;

import com.alibaba.fastjson.JSONObject;
import com.hanggle.base.BaseController;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * description: 用户登录模块<br/>
 * author: zh <br/>
 * date: 2018/3/12 <br/>
 */
@Api(value = "", tags = "用户")
@RestController
@RequestMapping("/user")
public class BdUserController extends BaseController {

    @RequestMapping(value = "/info" , method = RequestMethod.GET)
    @ResponseBody
    public String test(HttpServletRequest request, HttpServletResponse response){

        JSONObject obj = new JSONObject();
        String [] roles = {"/documentation", "index",  "/permission", "admin"};
        obj.put("roles", roles);
        obj.put("name", "tom");
        obj.put("username", "admin");
        obj.put("avatar", "avatar.gif");
        obj.put("introduction", "this is introduction");
        return obj.toString();
    }

}

