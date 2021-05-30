package com.oskyhang.system.mapper;

import com.hanggle.frames.base.BaseMapper;
import com.oskyhang.system.entity.SysUser;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * @author hanggle
 */
@Repository
public interface SysUserMapper extends BaseMapper<SysUser> {

    SysUser getUserInfo(Map<String, Object> params);
}
