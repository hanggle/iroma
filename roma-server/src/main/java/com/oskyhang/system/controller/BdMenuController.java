package com.oskyhang.system.controller;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Throwables;
import com.hanggle.frames.base.BaseController;
import com.hanggle.frames.base.Response;
import com.hanggle.frames.base.ErrorCode;
import com.oskyhang.system.dto.MenuQueryParam;
import com.oskyhang.system.dto.SelectDto;
import com.oskyhang.system.dto.MenuTreeDto;
import com.oskyhang.system.entity.BdMenu;
import com.oskyhang.system.service.BdMenuService;
import com.sun.org.apache.xpath.internal.operations.Bool;
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
    public Response<BdMenu> select(@RequestParam Long id) {
        BdMenu bdMenu = bdMenuService.load(id);
        return Response.success(bdMenu);
    }

    /**
     * 查询菜单列表
     * @param menuQueryParam 参数
     * @return 菜单list
     */
    @RequestMapping(value="/list", method= RequestMethod.POST)
    public Response<List<BdMenu>> list(@RequestBody MenuQueryParam menuQueryParam){
        List<BdMenu> list = bdMenuService.list(menuQueryParam);
        return Response.success(list);
    }

    @RequestMapping(value="/menuTree", method= RequestMethod.GET)
    public Response<MenuTreeDto> menuTree(){
        MenuTreeDto menuTreeDto = bdMenuService.selectMenuTree();
        return Response.success(menuTreeDto);
    }

    /**
     * 菜单下拉选择
     * @return
     */
    @RequestMapping(value="/menuSelect", method= RequestMethod.GET)
    public Response<List<SelectDto>> menuSelect(MenuQueryParam menuQueryParam){
        List<SelectDto> menuTreeDto = bdMenuService.menuSelect(menuQueryParam);
        return Response.success(menuTreeDto);
    }

    /**
     *
     * @param bdMenu 菜单
     * @return id
     */
    @RequestMapping(value = "/insert", method= RequestMethod.POST)
    public Response<Boolean> insert(@RequestBody BdMenu bdMenu){

        try {
            bdMenuService.insertAndUpdate(bdMenu);
            return Response.success();
        } catch (Exception e) {
            log.error("roma[]BdMenuController[]insert find exception! case:{}", Throwables.getStackTraceAsString(e));
            return Response.fail(ErrorCode.CREATE_FAIL);
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(BdMenu bdMenu) {
        bdMenuService.update(bdMenu);
        return "";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete(@RequestParam Long id) {
        bdMenuService.delete(id);
        return JSONObject.toJSONString(Response.success(""));
    }
}
