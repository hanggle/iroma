package com.oskyhang.system.mapper;


import com.oskyhang.system.entity.BdMenu;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface BdMenuMapper {
    int deleteByPrimaryKey(String id);

    int insert(BdMenu record);

    int insertSelective(BdMenu record);

    BdMenu selectByPrimaryKey(String id);

    List<BdMenu> selectMenuList(Map<String, Object> params);

    List<BdMenu> selectMenuTree();

    int updateByPrimaryKeySelective(BdMenu record);

    int updateByPrimaryKey(BdMenu bdMenu);
}