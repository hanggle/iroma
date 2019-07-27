package com.oskyhang.system.controller;

import com.hanggle.frames.Properties.ShiroRedisConfig;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

/**
 * @description:
 * @author: hanggle
 * @date: 2019/4/16
 */
public class LoginControllerTest extends BaseTestController{

    @Autowired
    private ShiroRedisConfig shiroRedisConfig;

    @Test
    public void notLogin() {
        System.out.println("ww");
        System.out.println(shiroRedisConfig.getHost());
    }
}