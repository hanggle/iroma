package com.oskyhang.system.controller;

import com.alibaba.fastjson.JSONObject;
import com.hanggle.util.ResultUtil;
import com.oskyhang.system.entity.BdMenu;
import com.oskyhang.system.service.BdMenuService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * description: <br/>
 * author: zh <br/>
 * date: 2018/4/10 <br/>
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BdMenuControllerTest {

    @Autowired
    private BdMenuService bdMenuService;

    @Test
    public void list() throws Exception {

        List<BdMenu> list = bdMenuService.selectMenuList();
        System.out.println(JSONObject.toJSONString(list));
    }

    public static void main(String[] args){
        JSONObject obj = new JSONObject();
        obj.put("a", 23);
        obj.put("b", 23);
        String str = ResultUtil.success(obj).toString();
        System.out.println(str);
    }


    @Test
    public void menuTree() throws Exception {
        System.out.println(bdMenuService.selectMenuTree().get(1));
    }
}