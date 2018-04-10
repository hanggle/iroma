package com.oskyhang.system.handler;

import com.alibaba.fastjson.JSONArray;
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
@Api(value = "菜单设置", tags = "菜单管理")
@RestController
@RequestMapping("/menu")
public class BdMenuController extends BaseController {
    private static Logger logger = LoggerFactory.getLogger(BdMenuController.class);

    @Autowired
    private BdMenuService bdMenuService;

    @ApiOperation(value = "查询菜单", notes = "根据菜单ID详细信息")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "String")
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public String select(@PathVariable("id") String id) {
        logger.info(id);
        return "";
    }

    @ApiOperation(value="获取菜单列表", notes="权限下所有菜单")
    @RequestMapping(value="/list", method= RequestMethod.GET)
    @ResponseBody
    public String list(HttpServletRequest request, HttpServletResponse response){

        List<BdMenu> list = bdMenuService.selectMenuList("is_menu desc,order_code");
        return JSONArray.toJSONString(list);
    }

    @ApiOperation(value = "新增菜单", notes = "")
    @RequestMapping(value = "", method= RequestMethod.POST)
    public String insert(@RequestBody BdMenu bdMenu, HttpServletRequest request){

        int i = bdMenuService.insert(bdMenu);
        logger.debug(String.valueOf(i));
        if(i > -1)
            throw  new NullPointerException();
        return "";
    }

    @ApiOperation(value = "更新菜单", notes = "")
    @ApiImplicitParam(name = "id", value = "菜单ID", required = true, dataType = "String")
    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public String update(@PathVariable("id") String id) {
        logger.info(id);
        return "";
    }

    @ApiOperation(value = "删除菜单", notes = "根据ID删除菜单，逻辑删除，不是物理删除")
    @ApiImplicitParam(name = "id", value = "菜单ID", required = true, dataType = "String")
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable("id") String id, HttpServletRequest request) {
        logger.info(id);
        return "";
    }
}
