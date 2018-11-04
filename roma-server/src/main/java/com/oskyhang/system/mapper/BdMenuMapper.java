package com.oskyhang.system.mapper;


import com.frames.base.BaseMapper;
import com.oskyhang.system.entity.BdMenu;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
/**
 * description: <br/>
 * @author: zh <br/>
 * date: 2018/3/12 <br/>
 */
@Repository
public interface BdMenuMapper extends BaseMapper<BdMenu> {

    List<BdMenu> selectMenuList(Map<String, Object> params);

    List<BdMenu> selectMenuTree();
}
