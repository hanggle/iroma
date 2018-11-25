package com.oskyhang.system.controller;

import com.alibaba.fastjson.JSONObject;
import com.hanggle.frames.base.BaseController;
import com.hanggle.frames.base.BaseResult;
import com.hanggle.frames.util.Response;
import com.oskyhang.system.dto.BdMenuDto;
import com.oskyhang.system.entity.BdMenu;
import com.oskyhang.system.service.BdMenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @Description:
 * @author : z.hang
 * @Date: 2018-01-14
 * Time: 18:16
 */
@RestController
@RequestMapping("/menu")
@Slf4j
public class BdMenuController extends BaseController {

    @Autowired
    private BdMenuService bdMenuService;

    @RequestMapping(value = "/get", method= RequestMethod.GET)
    public BaseResult<BdMenu> select(@RequestParam Long id) {
        BdMenu bdMenu = bdMenuService.selectByPrimaryKey(id);
        return Response.success(bdMenu);
    }

    /**
     * 查询菜单列表
     * @param menuDto queryType=1 包括根菜单
     * @return 菜单list
     */
    @RequestMapping(value="/list", method= RequestMethod.POST)
    public BaseResult<List<BdMenu>> list(@RequestBody BdMenuDto bdMenuDto){
        log.info("list:{}", bdMenuDto);
        List<BdMenu> list = bdMenuService.list(bdMenuDto);
        return Response.success(list);
    }

    @RequestMapping(value="/menuTree", method= RequestMethod.GET)
    public BaseResult<BdMenu> menuTree(){
        List<BdMenu> list = bdMenuService.selectMenuTree();
        return Response.success(list);
    }

    @RequestMapping(value="/oneLevelMenu", method= RequestMethod.GET)
    public BaseResult<List<BdMenu>> oneLevelMenu(){

        Map<String, Object> params = new HashMap<>(16);
        params.put("level", "1");

        List<BdMenu> list = bdMenuService.list(params);
        return Response.success(list);
    }

    @RequestMapping(value = "/insert", method= RequestMethod.POST)
    public String insert(@RequestBody BdMenu bdMenu){
        int i = bdMenuService.insert(bdMenu);
        return "";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(BdMenu bdMenu) {
        bdMenuService.updateByPrimaryKey(bdMenu);
        return "";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String delete(@RequestParam("id") Long id) {
        bdMenuService.deleteByPrimaryKey(id);
        return JSONObject.toJSONString(Response.success(""));
    }
}
