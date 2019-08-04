package com.oskyhang.system.controller;

import com.alibaba.fastjson.JSONObject;
import com.hanggle.frames.Properties.PrivilegeProperties;
import com.hanggle.frames.base.BaseController;
import com.hanggle.frames.base.Response;
import com.hanggle.frames.base.ResponseStatus;
import com.hanggle.frames.config.MyProperties;
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
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.*;


/**
 * description: 用户登录模块<br/>
 * author: zh <br/>
 * date: 2018/3/12 <br/>
 */
@Slf4j
@RestController
@RequestMapping("/api/base/login")
@EnableConfigurationProperties({PrivilegeProperties.class, MyProperties.class})
public class LoginController extends BaseController {

    @Autowired
    private RedisProperties redisProperties;
//    @Autowired
//    private PrivilegeProperties2 privailege;
//    @Autowired
//    private PrivilegeProperties privailege2;
    @Autowired
    private PrivilegeProperties testProperties;
    @Autowired
    private com.hanggle.frames.config.MyProperties myProperties;

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
        obj.put("code", "1");
        obj.put("desc", "sss");
        return Response.success(obj);
    }

    @RequestMapping(value = "/notLogin")
    public Response<JSONObject> notLogin(){
        return Response.fail(ResponseStatus.FAIL_NOTLOGIN);
    }

    @GetMapping(value = "/token")
    public Response<String> token(){
//        log.debug(privailege.toString());
//        log.debug(privailege2.toString());
        log.debug(testProperties.toString());
        log.debug(myProperties.toString());
        return Response.success(testProperties.toString());
    }
}

