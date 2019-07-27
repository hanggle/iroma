package com.oskyhang.system.mapper;

import com.oskyhang.system.entity.BdRole;
import org.springframework.stereotype.Repository;

@Repository
public interface BdRoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BdRole record);

    int insertSelective(BdRole record);

    BdRole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BdRole record);

    int updateByPrimaryKey(BdRole record);
}