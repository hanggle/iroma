package com.oskyhang.system.handler;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.microhang.base.BaseController;
import com.oskyhang.system.entity.BdMenu;
import com.oskyhang.system.service.BdMenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Description:
 * User: z.hang
 * Date: 2018-01-14
 * Time: 18:16
 */
@Controller
public class BdMenuController extends BaseController {
    private static Logger logger = LoggerFactory.getLogger(BdMenuController.class);
    @Autowired
    private BdMenuService bdMenuService;

    @RequestMapping("/system/user/menu")
    @ResponseBody
    public String getBdMenu(HttpServletRequest request, HttpServletResponse response){

        List<BdMenu> list = bdMenuService.selectMenuList("is_menu desc,order_code");
        return JSONArray.toJSONString(list);
    }

    @RequestMapping("/system/user/create")
    public String createBdMenu(HttpServletRequest request, HttpServletResponse response){

        BdMenu bdMenu = new BdMenu();
        bdMenu.setMenuName("test");

        int i = bdMenuService.insert(bdMenu);
        logger.debug(String.valueOf(i));
        return "";
    }
    @RequestMapping("/testExc")
    @ResponseBody
    public Object testExc(HttpServletRequest request, HttpServletResponse response){
        JSONObject obj = new JSONObject();
        obj.put("code", "1");
        obj.put("desc", "sss");
        BdMenu bdMenu = bdMenuService.selectByPrimaryKey("ED9A588726A211E8935154E1AD007815");
        return bdMenu;
    }
}
