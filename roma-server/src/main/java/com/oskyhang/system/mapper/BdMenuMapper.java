package com.oskyhang.system.mapper;


import com.oskyhang.system.entity.BdMenu;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BdMenuMapper {
    int deleteByPrimaryKey(String id);

    int insert(BdMenu record);

    int insertSelective(BdMenu record);

    BdMenu selectByPrimaryKey(String id);

    List<BdMenu> selectMenuList(@Param("orderCode") String orderCode);

    int updateByPrimaryKeySelective(BdMenu record);

    int updateByPrimaryKey(BdMenu record);
}