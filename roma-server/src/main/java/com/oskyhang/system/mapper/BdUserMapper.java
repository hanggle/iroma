package com.oskyhang.system.mapper;

import com.hanggle.frames.base.BaseMapper;
import com.oskyhang.system.entity.BdUser;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * @author hanggle
 */
@Repository
public interface BdUserMapper extends BaseMapper<BdUser> {

    BdUser getUserInfo(Map<String, Object> params);
}
