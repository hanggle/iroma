package com.oskyhang.system.controller;

import com.hanggle.frames.Properties.ShiroRedisConfig;
import com.hanggle.frames.util.RedisUtils;
import com.hanggle.frames.util.SpringContextUtils;
import com.hanggle.utils.HanggleUtil;
import com.oskyhang.system.entity.BdMenu;
import com.oskyhang.system.mapper.BdMenuMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * description: <br/>
 * author: zh <br/>
 * date: 2018/4/13 <br/>
 */
public class BdUserControllerTest extends BaseTestController{

    @Autowired
    private ShiroRedisConfig shiroRedisConfig;

    @Test
    public void test1() {
        System.out.println(shiroRedisConfig.getHost());
    }
    @Test
    public void test2() {
        System.out.println(RedisUtils.getLock("lockTest", HanggleUtil.getUUID(), "100"));
    }

    @Test
    public void test(){

    }
}
