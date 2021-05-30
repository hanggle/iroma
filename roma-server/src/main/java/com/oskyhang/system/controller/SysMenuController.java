package com.oskyhang.system.controller;

import com.hanggle.frames.base.BaseController;
import com.oskyhang.system.dto.LoginUser;
import com.oskyhang.system.dto.MenuQueryParam;
import com.oskyhang.system.dto.SelectDto;
import com.oskyhang.system.dto.MenuTreeDto;
import com.oskyhang.system.entity.SysMenu;
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
public class SysMenuController extends BaseController {

    @Autowired
    private BdMenuService bdMenuService;

    @RequestMapping(value = "/get", method= RequestMethod.GET)
    public SysMenu select(@RequestParam Long id) {
        SysMenu sysMenu = bdMenuService.load(id);
        return sysMenu;
    }

    /**
     * 查询菜单列表
     * @param menuQueryParam 参数
     * @return 菜单list
     */
    @RequestMapping(value="/list")
    public List<SysMenu> list(MenuQueryParam menuQueryParam){
        LoginUser loginUser = new LoginUser();
        loginUser.setPersonName("QQQQQ");
        List<SysMenu> list = bdMenuService.list(menuQueryParam, loginUser);
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
        return bdMenuService.menuSelect(menuQueryParam);
    }

    /**
     *
     * @param sysMenu 菜单
     * @return id
     */
    @PostMapping(value = "/insert")
    public Boolean insert(@RequestBody SysMenu sysMenu){
        bdMenuService.insertAndUpdate(sysMenu);
        return true;
    }

    @PostMapping(value = "/update")
    public Boolean update(SysMenu sysMenu) {
        return bdMenuService.update(sysMenu) > 0;
    }

    @PostMapping(value = "/delete")
    public Boolean delete(@RequestBody Long id) {
        return bdMenuService.delete(id) > 0;
    }
}
