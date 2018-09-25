package com.oskyhang.system.controller;

import com.alibaba.fastjson.JSONObject;
import com.frames.base.BaseController;
import com.frames.util.ResultUtil;
import com.oskyhang.system.entity.BdMenu;
import com.oskyhang.system.service.BdMenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * Description:
 * User: z.hang
 * Date: 2018-01-14
 * Time: 18:16
 */
@RestController
@RequestMapping("/menu")
public class BdMenuController extends BaseController {
    private static Logger logger = LoggerFactory.getLogger(BdMenuController.class);

    @Autowired
    private BdMenuService bdMenuService;

    @RequestMapping(value = "", method= RequestMethod.GET)
    public String select(String id, String data) {
        BdMenu bdMenu = bdMenuService.selectByPrimaryKey(id);
        return JSONObject.toJSONString(bdMenu);
    }

    @ResponseBody
    public String list(HttpServletRequest request, HttpServletResponse response, @RequestBody String data){
        List<BdMenu> list = bdMenuService.selectMenuList();
        return JSONObject.toJSONString(list);
    }

    @ResponseBody
    public String menuTree(HttpServletRequest request, HttpServletResponse response){
        List<BdMenu> list = bdMenuService.selectMenuTree();
        return JSONObject.toJSONString(list);
    }

    @RequestMapping(value="/oneLevelMenu", method= RequestMethod.GET)
    public String oneLevelMenu(HttpServletRequest request, HttpServletResponse response){

        Map<String, Object> params = new HashMap<>();
        params.put("level", "1");

        List<BdMenu> list = bdMenuService.selectMenuList(params);
        return JSONObject.toJSONString(list);
    }

    @RequestMapping(value = "", method= RequestMethod.POST)
    public String insert(@RequestBody BdMenu bdMenu, HttpServletRequest request){
        int i = bdMenuService.insert(bdMenu);
        System.out.println(i);
        return "";
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public String update(BdMenu bdMenu) {
        bdMenuService.updateByPrimaryKey(bdMenu);
        return "";
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable("id") String id, HttpServletRequest request) {
        bdMenuService.deleteByPrimaryKey(id);
        return JSONObject.toJSONString(ResultUtil.success(""));
    }
}
