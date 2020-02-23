package com.oskyhang.system.controller;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Throwables;
import com.hanggle.frames.base.BaseController;
import com.hanggle.frames.base.Response;
import com.hanggle.frames.base.ErrorCode;
import com.oskyhang.system.dto.LoginUser;
import com.oskyhang.system.dto.MenuQueryParam;
import com.oskyhang.system.dto.SelectDto;
import com.oskyhang.system.dto.MenuTreeDto;
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
@Slf4j
@RestController
@RequestMapping("/api/user/menu")
public class BdMenuController extends BaseController {

    @Autowired
    private BdMenuService bdMenuService;

    @RequestMapping(value = "/get", method= RequestMethod.GET)
    public BdMenu select(@RequestParam Long id) {
        BdMenu bdMenu = bdMenuService.load(id);
        return bdMenu;
    }

    @RequestMapping(value = "/test", method= RequestMethod.POST)
    public Long test(@RequestParam Long id) {
        BdMenu bdMenu = bdMenuService.load(id);
        return 89L;
    }

    /**
     * 查询菜单列表
     * @param menuQueryParam 参数
     * @return 菜单list
     */
    @RequestMapping(value="/list")
    public List<BdMenu> list(MenuQueryParam menuQueryParam){
        LoginUser loginUser = new LoginUser();
        loginUser.setPersonName("QQQQQ");
        List<BdMenu> list = bdMenuService.list(menuQueryParam, loginUser);
        return list;
    }

    @RequestMapping(value="/menuTree", method= RequestMethod.GET)
    public MenuTreeDto menuTree(){
        MenuTreeDto menuTreeDto = bdMenuService.selectMenuTree();
        return menuTreeDto;
    }

    /**
     * 菜单下拉选择
     * @return
     */
    @RequestMapping(value="/menuSelect", method= RequestMethod.GET)
    public List<SelectDto> menuSelect(MenuQueryParam menuQueryParam){
        List<SelectDto> menuTreeDto = bdMenuService.menuSelect(menuQueryParam);
        return menuTreeDto;
    }

    /**
     *
     * @param bdMenu 菜单
     * @return id
     */
    @RequestMapping(value = "/insert", method= RequestMethod.POST)
    public Boolean insert(@RequestBody BdMenu bdMenu){
        bdMenuService.insertAndUpdate(bdMenu);
        return true;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(BdMenu bdMenu) {
        bdMenuService.update(bdMenu);
        return "";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete(@RequestParam Long id) {
        bdMenuService.delete(id);
        return JSONObject.toJSONString("");
    }
}
