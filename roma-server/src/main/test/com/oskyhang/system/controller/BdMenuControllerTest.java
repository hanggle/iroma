package com.oskyhang.system.controller;

import com.alibaba.fastjson.JSONObject;
import com.oskyhang.system.service.BdMenuService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * description: <br/>
 * author: zh <br/>
 * date: 2018/4/10 <br/>
 */

public class BdMenuControllerTest extends BaseTestController {

    @Autowired
    private BdMenuService bdMenuService;

//    @Autowired
//    private RedisUtils redisTemplate;

    @Test
    public void list() {

//        List<BdMenu> list = bdMenuService.list();
//        System.out.println(JSONObject.toJSONString(list));
    }

    public static void main(String[] args){
        JSONObject obj = new JSONObject();
        obj.put("a", 23);
        obj.put("b", 23);
        String str = Response2.success(obj).toString();
        System.out.println(str);
    }


    @Test
    public void menuTree() {
        //System.out.println(bdMenuService.selectMenuTree().get(1));
    }

    @Test
    public void redisTest() {
        String b;
//        b = redisTemplate.getStr("hello");

//        System.out.println(b);
    }
}
