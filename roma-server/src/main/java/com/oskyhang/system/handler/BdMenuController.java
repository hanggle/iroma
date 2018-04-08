package com.oskyhang.system.handler;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hanggle.base.BaseController;
import com.oskyhang.system.entity.BdMenu;
import com.oskyhang.system.service.BdMenuService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Description:
 * User: z.hang
 * Date: 2018-01-14
 * Time: 18:16
 */
@Api(value = "菜单设置")
@RestController
@RequestMapping("/menu")
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

    @ApiOperation(value = "获取用户详细信息", notes = "根据url的id来获取用户详细信息")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long")
    @RequestMapping(value = "/api/{id}", method = RequestMethod.GET)
    public String getUser(@PathVariable Long id) {
        return "";
    }

    @ApiOperation(value="获取用户列表", notes="")
    @RequestMapping(value={""}, method= RequestMethod.GET)
    @ResponseBody
    public String list(HttpServletRequest request, HttpServletResponse response){

        List<BdMenu> list = bdMenuService.selectMenuList("is_menu desc,order_code");
        return JSONArray.toJSONString(list);
    }

    @RequestMapping("/postUser")
    public String createBdMenu(@RequestBody BdMenu bdMenu){

        System.out.println(bdMenu);
        BdMenu bdMenu2 = new BdMenu();

        int i = bdMenuService.insert(bdMenu2);
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
