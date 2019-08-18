package com.oskyhang.system.controller;

import com.hanggle.frames.properties.ShiroRedisProperties;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @description:
 * @author: hanggle
 * @date: 2019/4/16
 */
public class LoginControllerTest extends BaseTestController{

    @Autowired
    private ShiroRedisProperties shiroRedisProperties;

    @Test
    public void notLogin() {
        System.out.println("ww");
        System.out.println(shiroRedisProperties.getHost());
    }
}