package com.oskyhang.system.mapper;

import com.oskyhang.system.entity.BdMenu;
import com.oskyhang.system.entity.BdMenuExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BdMenuMapper {
    int countByExample(BdMenuExample example);

    int deleteByExample(BdMenuExample example);

    int deleteByPrimaryKey(String menuId);

    int insert(BdMenu record);

    int insertSelective(BdMenu record);

    List<BdMenu> selectByExample(BdMenuExample example);

    BdMenu selectByPrimaryKey(String menuId);

    int updateByExampleSelective(@Param("record") BdMenu record, @Param("example") BdMenuExample example);

    int updateByExample(@Param("record") BdMenu record, @Param("example") BdMenuExample example);

    int updateByPrimaryKeySelective(BdMenu record);

    int updateByPrimaryKey(BdMenu record);
}