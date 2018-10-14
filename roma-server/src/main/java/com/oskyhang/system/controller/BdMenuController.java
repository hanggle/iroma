package com.oskyhang.system.controller;

import com.alibaba.fastjson.JSONObject;
import com.frames.base.BaseController;
import com.frames.util.ResultUtil;
import com.oskyhang.system.entity.BdMenu;
import com.oskyhang.system.service.BdMenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Description:
 * User: z.hang
 * Date: 2018-01-14
 * Time: 18:16
 */
@RestController
@RequestMapping("/menu")
@Slf4j
public class BdMenuController extends BaseController {

    @Autowired
    private BdMenuService bdMenuService;

    @RequestMapping(value = "", method= RequestMethod.GET)
    public String select(Long id) {
        BdMenu bdMenu = bdMenuService.selectByPrimaryKey(id);
        return JSONObject.toJSONString(bdMenu);
    }

    @ResponseBody
    @RequestMapping(value="/list", method= RequestMethod.POST)
    public String list(@RequestBody String data){
        List<BdMenu> list = bdMenuService.selectMenuList();
        return JSONObject.toJSONString(list);
    }

    @ResponseBody
    @RequestMapping(value="/menuTree", method= RequestMethod.GET)
    public String menuTree(){
        List<BdMenu> list = bdMenuService.selectMenuTree();
        return JSONObject.toJSONString(list);
    }

    @RequestMapping(value="/oneLevelMenu", method= RequestMethod.GET)
    public String oneLevelMenu(){

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

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String delete(@RequestParam("id") Long id) {
        bdMenuService.deleteByPrimaryKey(id);
        return JSONObject.toJSONString(ResultUtil.success(""));
    }
}
