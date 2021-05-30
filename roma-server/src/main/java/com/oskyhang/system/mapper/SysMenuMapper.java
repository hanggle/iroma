package com.oskyhang.system.mapper;


import com.hanggle.frames.base.BaseMapper;
import com.oskyhang.system.dto.SelectDto;
import com.oskyhang.system.entity.SysMenu;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * description: <br/>
 * @author: zh <br/>
 * date: 2018/3/12 <br/>
 */
@Repository
public interface SysMenuMapper extends BaseMapper<SysMenu> {

    /**
     *  菜单下拉选择
     * @return SelectDto
     */
    List<SelectDto> menuSelect();
}
