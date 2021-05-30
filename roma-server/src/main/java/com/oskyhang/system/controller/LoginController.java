package com.oskyhang.system.controller;

import com.alibaba.fastjson.JSONObject;
import com.hanggle.frames.base.BaseController;
import com.hanggle.frames.base.DataThreadLocal;
import com.hanggle.frames.base.ErrorMsg;
import com.hanggle.frames.base.Response;
import com.hanggle.frames.constant.RedisKey;
import com.hanggle.frames.exception.ServiceException;
import com.hanggle.frames.properties.PrivilegeProperties;
import com.hanggle.frames.util.RedisUtils;
import com.hanggle.utils.CommonUtil;
import com.oskyhang.system.dto.LoginUser;
import com.oskyhang.system.entity.SysUser;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * description: 用户登录模块<br/>
 * author: zh <br/>
 * date: 2018/3/12 <br/>
 */
@Slf4j
@RestController
@RequestMapping("/api/user/login")
@EnableConfigurationProperties({PrivilegeProperties.class})
public class LoginController extends BaseController {

    @Autowired
    private RedisUtils redisUtils;


    @RequestMapping(value = "/login", method= RequestMethod.POST)
    public LoginUser login(@RequestBody SysUser sysUser){
        String loginName = sysUser.getUsername();
        String loginPwd = sysUser.getPassword();
        try {
            UsernamePasswordToken token = new UsernamePasswordToken(loginName, loginPwd);
            Subject subject = SecurityUtils.getSubject();
            subject.login(token);
            String username = (String) subject.getPrincipal();
            LoginUser loginUser = new LoginUser();
            String sessionId = String.valueOf(subject.getSession().getId());
            String userToken = CommonUtil.UUID();
            loginUser.setToken(userToken);
            redisUtils.set(RedisKey.REDIS_KEY_TOKEN + userToken, sessionId);
            DataThreadLocal.putCurrentLoginUser(loginUser);
            return loginUser;
        } catch (DisabledAccountException e) {
            throw new ServiceException(ErrorMsg.ACCOUNT_FREEZE_ERROR);
        } catch (AuthenticationException e) {
            throw new ServiceException(ErrorMsg.ACCOUNT_AUTH_ERROR);
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public Response<JSONObject> logout(){
        JSONObject obj = new JSONObject();
        obj.put("code", "1");
        obj.put("desc", "sss");
        DataThreadLocal.removeLoginUser();
        return Response.success(obj);
    }

    @RequestMapping(value = "/notLogin")
    public Response<JSONObject> notLogin(){
        return Response.fail(ErrorMsg.FAIL_NOT_LOGIN);
    }
}

