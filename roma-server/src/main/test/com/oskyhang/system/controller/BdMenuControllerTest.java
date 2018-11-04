package com.oskyhang.system.controller;

import com.alibaba.fastjson.JSONObject;
import com.frames.util.*;
import com.oskyhang.system.entity.BdMenu;
import com.oskyhang.system.service.BdMenuService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;

/**
 * description: <br/>
 * author: zh <br/>
 * date: 2018/4/10 <br/>
 */

public class BdMenuControllerTest extends BaseTestController {

    @Autowired
    private BdMenuService bdMenuService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private BaseRedisDao redisDAO;

    @Test
    public void list() {

        List<BdMenu> list = bdMenuService.selectMenuList();
        System.out.println(JSONObject.toJSONString(list));
    }

    public static void main(String[] args){
        JSONObject obj = new JSONObject();
        obj.put("a", 23);
        obj.put("b", 23);
        String str = Response.success(obj).toString();
        System.out.println(str);
    }


    @Test
    public void menuTree() {
        System.out.println(bdMenuService.selectMenuTree().get(1));
    }

    @Test
    public void redisTest() {
        boolean b;
        b = redisDAO.exists("hello");
        redisDAO.set("hello", "world");
        Object obj = redisDAO.get("hello");

        System.out.println(123);
    }
}