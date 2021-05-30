package com.oskyhang.system.mapper;

import com.oskyhang.system.entity.SysPermission;
import org.springframework.stereotype.Repository;

@Repository
public interface SysPermissionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysPermission record);

    int insertSelective(SysPermission record);

    SysPermission selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysPermission record);

    int updateByPrimaryKey(SysPermission record);
}