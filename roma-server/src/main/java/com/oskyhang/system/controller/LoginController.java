package com.oskyhang.system.controller;

import com.alibaba.fastjson.JSONObject;
import com.hanggle.frames.base.BaseController;
import com.hanggle.frames.base.Response;
import com.hanggle.frames.base.ResponseStatus;
import com.hanggle.frames.config.RedisProperties;
import com.oskyhang.system.dto.LoginUser;
import com.oskyhang.system.entity.BdUser;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * description: 用户登录模块<br/>
 * author: zh <br/>
 * date: 2018/3/12 <br/>
 */
@RestController
@RequestMapping("/api/base/login")
@Slf4j
public class LoginController extends BaseController {

    @Autowired
    private RedisProperties redisProperties;

    @RequestMapping(value = "/login", method= RequestMethod.POST)
    public Response<LoginUser> login(@RequestBody BdUser bdUser){
        String loginName = bdUser.getUsername();
        String loginPwd = bdUser.getPassword();
        try {
            UsernamePasswordToken token = new UsernamePasswordToken(loginName, loginPwd);
            Subject subject = SecurityUtils.getSubject();
            subject.login(token);
            String username = (String) subject.getPrincipal();
            LoginUser loginUser = new LoginUser();
            String sessionId = String.valueOf(subject.getSession().getId());
            loginUser.setToken(sessionId);
            return Response.success(loginUser);
        } catch (DisabledAccountException e) {
            return Response.fail(ResponseStatus.ACCOUNT_FREEZE_ERROR);
        } catch (AuthenticationException e) {
            return Response.fail(ResponseStatus.ACCOUNT_AUTH_ERROR);
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public Response<JSONObject> logout(){
        JSONObject obj = new JSONObject();
        obj.put("status", "1");
        obj.put("desc", "sss");
        return Response.success(obj);
    }

    @RequestMapping(value = "/notLogin")
    public Response<JSONObject> notLogin(){
        return Response.fail(ResponseStatus.FAIL_NOTLOGIN);
    }

    @RequestMapping(value = "/token")
    public Response<String> token(){

        return Response.success("OK");
    }
}

