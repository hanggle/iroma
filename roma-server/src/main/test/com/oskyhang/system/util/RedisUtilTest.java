package com.oskyhang.system.util;

import com.hanggle.frames.util.RedisUtils;
import com.oskyhang.system.controller.BaseTestController;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;

import static org.junit.Assert.*;

/**
 * @description:
 * @author: hanggle
 * @date: 2019/4/7
 */
public class RedisUtilTest extends BaseTestController {

    @Test
    public void RedisUtilss() {
        String a = RedisUtils.getStr("a");
        System.out.println(a);
    }

    @Test
    public void test2() {
        boolean a = RedisUtils.getLock("a", "2222","100");
        System.out.println(a);
    }

}