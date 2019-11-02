package com.oskyhang.system.service;


import com.oskyhang.system.dto.LoginUser;
import com.oskyhang.system.dto.MenuQueryParam;
import com.oskyhang.system.dto.SelectDto;
import com.oskyhang.system.dto.MenuTreeDto;
import com.oskyhang.system.entity.BdMenu;

import java.util.List;

/**
 * Description:
 * User: z.hang <br/>
 * Date: 2018-01-14 <br/>
 * Time: 18:24 <br/>
 */
public interface BdMenuService {

    void insertAndUpdate(BdMenu bdMenu);

    List<BdMenu> list(MenuQueryParam menuQueryParam, LoginUser loginUser);
    /**
     * 菜单树
     * @return list
     */
    MenuTreeDto selectMenuTree();

    /**
     * 查询菜单
     * @param id id
     * @return menu
     */
    BdMenu load(Long id);

    /**
     *  更新菜单
     * @param bdMenu menu
     * @return menu
     */
    int update(BdMenu bdMenu);

    /**
     *  删除菜单
     * @param id id
     * @return menu
     */
    int delete(Long id);

    /**
     * 菜单下拉选择
     *
     * @param menuQueryParam param
     * @return List<SelectDto>
     */
    List<SelectDto> menuSelect(MenuQueryParam menuQueryParam);
}
