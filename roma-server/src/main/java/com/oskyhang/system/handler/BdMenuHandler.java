package com.oskyhang.system.handler;

import com.alibaba.fastjson.JSONObject;
import com.oskyhang.system.entity.BdMenu;
import com.oskyhang.system.service.BdMenuService;
import com.oskyhang.utils.ZhUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Description:
 * User: z.hang
 * Date: 2018-01-14
 * Time: 18:16
 */
@Controller
public class BdMenuHandler {
    private static Logger logger = LoggerFactory.getLogger(BdMenuHandler.class);
    @Autowired
    private BdMenuService bdMenuService;

    @RequestMapping("/system/user/create")
    public String createBdMenu(HttpServletRequest request, HttpServletResponse response){

        BdMenu bdMenu = new BdMenu();
        bdMenu.setMenuId(ZhUtil.getUUID());
        bdMenu.setMenuName("test");
        bdMenu.setMenuSname("test");
        bdMenu.setMenuLevel("2");

        int i = bdMenuService.insert(bdMenu);
        logger.debug(String.valueOf(i));
        return "";
    }
    @RequestMapping("/login/login")
    @ResponseBody
    public String testExc(HttpServletRequest request, HttpServletResponse response){
        JSONObject obj = new JSONObject();
        obj.put("code", "1");
        obj.put("desc", "sss");
        return obj.toString();
    }
}
