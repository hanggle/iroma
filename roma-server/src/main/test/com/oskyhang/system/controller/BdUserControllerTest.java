package com.oskyhang.system.controller;

import com.hanggle.frames.properties.ShiroRedisProperties;
import com.hanggle.frames.util.RedisUtils;
import com.hanggle.utils.HanggleUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * description: <br/>
 * author: zh <br/>
 * date: 2018/4/13 <br/>
 */
public class BdUserControllerTest extends BaseTestController{

    @Autowired
    private ShiroRedisProperties shiroRedisProperties;

    @Test
    public void test1() {
        System.out.println(shiroRedisProperties.getHost());
    }
    @Test
    public void test2() {
        System.out.println(RedisUtils.getLock("lockTest", HanggleUtil.getUUID(), "100"));
    }

    @Test
    public void test(){

    }
}
