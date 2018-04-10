package com.oskyhang.system.handler;

import com.alibaba.fastjson.JSONObject;
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

        List<BdMenu> list = bdMenuService.selectMenuList("is_menu desc,order_code");
        System.out.println(JSONObject.toJSONString(list));
    }

}