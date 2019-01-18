package com.oskyhang.system.controller;

import com.alibaba.fastjson.JSONObject;
import com.hanggle.frames.base.BaseController;
import com.hanggle.frames.base.Response;
import com.hanggle.frames.base.ResponseCode;
import com.hanggle.frames.config.RedisProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * description: 用户登录模块<br/>
 * author: zh <br/>
 * date: 2018/3/12 <br/>
 */
@RestController
@RequestMapping("/base/login")
@Slf4j
public class LoginController extends BaseController {

    @Autowired
    private RedisProperties redisProperties;

    @RequestMapping(value = "/login", method= RequestMethod.POST)
    public Response<JSONObject> login(@RequestBody Map dataMap){
        JSONObject obj = new JSONObject();
        String datas = (String) dataMap.get("username");
        obj.put("code", "1o");
        obj.put("message", "sss");
        obj.put("token", "12wqs");
        return Response.success(obj);
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public Response<JSONObject> logout(){
        JSONObject obj = new JSONObject();
        obj.put("code", "1");
        obj.put("desc", "sss");
        return Response.success(obj);
    }

    @RequestMapping(value = "/notLogin")
    public Response<JSONObject> unLogin(){
        return Response.fail(ResponseCode.FAIL_NOTLOGIN.code(), ResponseCode.FAIL_NOTLOGIN.message());
    }
}

