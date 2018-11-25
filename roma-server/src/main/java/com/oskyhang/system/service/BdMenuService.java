package com.oskyhang.system.service;


import com.oskyhang.system.dto.BdMenuDto;
import com.oskyhang.system.entity.BdMenu;

import java.util.List;
import java.util.Map;

/**
 * Description:
 * User: z.hang <br/>
 * Date: 2018-01-14 <br/>
 * Time: 18:24 <br/>
 */
public interface BdMenuService {

    int insert(BdMenu bdMenu);

    List<BdMenu> list(BdMenuDto bdMenuDto);

    List<BdMenu> list(Map <String, Object> params);

    List<BdMenu> selectMenuTree();

    BdMenu selectByPrimaryKey(Long id);

    int updateByPrimaryKey(BdMenu bdMenu);

    int deleteByPrimaryKey(Long id);
}
