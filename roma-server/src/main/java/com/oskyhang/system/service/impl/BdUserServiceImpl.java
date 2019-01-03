package com.oskyhang.system.service.impl;

import com.google.common.base.Objects;
import com.hanggle.frames.base.Page;
import com.hanggle.frames.util.IdUtil;
import com.hanggle.frames.util.PageUtil;
import com.oskyhang.system.dto.MenuQueryParam;
import com.oskyhang.system.dto.MenuTreeDto;
import com.oskyhang.system.dto.QueryParam;
import com.oskyhang.system.dto.SelectDto;
import com.oskyhang.system.entity.BdMenu;
import com.oskyhang.system.entity.BdUser;
import com.oskyhang.system.mapper.BdMenuMapper;
import com.oskyhang.system.mapper.BdUserMapper;
import com.oskyhang.system.service.BdMenuService;
import com.oskyhang.system.service.BdUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.hanggle.frames.util.Arguments.notNull;

/**
 * Description: 菜单接口 <br/>
 * @author : z.hang <br/>
 * Date: 2018-01-14 <br/>
 * Time: 18:25 <br/>
 */
@Service
public class BdUserServiceImpl implements BdUserService {

    @Autowired
    private BdUserMapper bdUserMapper;

    @Override
    public void insertAndUpdate(BdUser bdUser) {
        bdUserMapper.insert(bdUser);
    }

    @Override
    public List<BdUser> list(QueryParam queryParam) {
        Map<String, Object> params = new HashMap<>();
        return bdUserMapper.list(params);
    }

    @Override
    public Page<BdUser> page(QueryParam queryParam) {
        Map<String, Object> params = PageUtil.initPageParam(queryParam.getPageNo(), queryParam.getPageSize());
        int count = bdUserMapper.count(params);
        List<BdUser> list = bdUserMapper.page(params);
        return new Page<>(count, list);
    }

    @Override
    public BdUser load(Long id) {
        return bdUserMapper.load(id);
    }

    @Override
    public int update(BdUser bdUser) {
        return bdUserMapper.update(bdUser);
    }

    @Override
    public int delete(Long id) {
        return bdUserMapper.delete(id);
    }
}
