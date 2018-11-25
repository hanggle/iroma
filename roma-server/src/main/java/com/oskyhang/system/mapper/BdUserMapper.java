package com.oskyhang.system.mapper;

import com.oskyhang.system.entity.BdUser;

public interface BdUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BdUser record);

    int insertSelective(BdUser record);

    BdUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BdUser record);

    int updateByPrimaryKey(BdUser record);
}
