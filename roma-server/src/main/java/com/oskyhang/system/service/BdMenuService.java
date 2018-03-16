package com.oskyhang.system.service;


import com.oskyhang.system.entity.BdMenu;

import java.util.List;

/**
 * Description:
 * User: z.hang <br/>
 * Date: 2018-01-14 <br/>
 * Time: 18:24 <br/>
 */
public interface BdMenuService {

    int insert(BdMenu bdMenu);

    List<BdMenu> selectMenuList(String orderCode);

    BdMenu selectByPrimaryKey(String id);
}
