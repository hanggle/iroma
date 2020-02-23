package com.hanggle.frames.shiro;

import com.hanggle.frames.constant.RedisKey;
import com.hanggle.frames.util.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.Serializable;

/**
 * @description:
 * @author: hanggle
 * @date: 2019/1/17
 */
@Slf4j
public class MySessionManager extends DefaultWebSessionManager {

    private static final String ACCESS_TOKEN = "access_token";

    private static final String REFERENCED_SESSION_ID_SOURCE = "Stateless request";

    @Autowired
    private RedisUtils redisUtils;

    public MySessionManager() {
        super();
    }

    @Override
    protected Serializable getSessionId(ServletRequest request, ServletResponse response) {
        String accessToken = request.getParameter(ACCESS_TOKEN);
        log.info("MySessionManager[]getSessionId[]sessionId:{}", accessToken);
        //如果请求头中有 Authorization 则其值为sessionId
        if (!Strings.isEmpty(accessToken)) {
            String sessionId = redisUtils.getStr(RedisKey.REDIS_KEY_TOKEN + accessToken);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE, REFERENCED_SESSION_ID_SOURCE);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID, sessionId);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID, Boolean.TRUE);
            return sessionId;
        } else {
            //否则按默认规则从cookie取sessionId
            return super.getSessionId(request, response);
        }
    }
}