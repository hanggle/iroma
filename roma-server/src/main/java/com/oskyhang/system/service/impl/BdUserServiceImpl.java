package com.oskyhang.system.service.impl;

import com.hanggle.frames.base.IdGenerator;
import com.hanggle.frames.base.Page;
import com.hanggle.frames.shiro.ShiroConfig;
import com.hanggle.frames.util.PageUtil;
import com.hanggle.utils.CommonUtil;
import com.oskyhang.system.dto.QueryParam;
import com.oskyhang.system.entity.BdUser;
import com.oskyhang.system.mapper.BdUserMapper;
import com.oskyhang.system.service.BdUserService;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public Long insert(BdUser bdUser) {
        String userName = bdUser.getUsername();
        String password = "123456";
        String algorithmName = ShiroConfig.algorithmName;
        String salt = CommonUtil.MD5(userName);
        int hashIteration = ShiroConfig.hashIteration;
        SimpleHash simpleHash = new SimpleHash(algorithmName, password, salt, hashIteration);
        bdUser.setPassword(String.valueOf(simpleHash));
        bdUser.setId(IdGenerator.getId());
        bdUserMapper.insert(bdUser);
        return bdUser.getId();
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

    @Override
    public BdUser getUserInfo(String loginName, String type) {
        Map<String, Object> hashMap = new HashMap<>();
        hashMap.put("username", loginName);
        hashMap.put("type", type);
        return bdUserMapper.getUserInfo(hashMap);
    }
}
