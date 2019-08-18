package com.oskyhang.system.mapper;

import com.oskyhang.system.entity.BdPermission;
import org.springframework.stereotype.Repository;

@Repository
public interface BdPermissionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BdPermission record);

    int insertSelective(BdPermission record);

    BdPermission selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BdPermission record);

    int updateByPrimaryKey(BdPermission record);
}