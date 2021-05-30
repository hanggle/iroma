package com.oskyhang.system.service.impl;

import com.hanggle.frames.base.IdGenerator;
import com.hanggle.frames.base.Page;
import com.hanggle.frames.shiro.ShiroConfig;
import com.hanggle.frames.util.PageUtil;
import com.hanggle.utils.CommonUtil;
import com.oskyhang.system.dto.QueryParam;
import com.oskyhang.system.entity.SysUser;
import com.oskyhang.system.mapper.SysUserMapper;
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
    private SysUserMapper sysUserMapper;

    @Override
    public Long insert(SysUser sysUser) {
        String userName = sysUser.getUsername();
        String password = "123456";
        String algorithmName = ShiroConfig.algorithmName;
        String salt = CommonUtil.MD5(userName);
        int hashIteration = ShiroConfig.hashIteration;
        SimpleHash simpleHash = new SimpleHash(algorithmName, password, salt, hashIteration);
        sysUser.setPassword(String.valueOf(simpleHash));
        sysUser.setId(IdGenerator.getId());
        sysUserMapper.insert(sysUser);
        return sysUser.getId();
    }

    @Override
    public List<SysUser> list(QueryParam queryParam) {
        Map<String, Object> params = new HashMap<>();
        return sysUserMapper.list(params);
    }

    @Override
    public Page<SysUser> page(QueryParam queryParam) {
        Map<String, Object> params = PageUtil.initPageParam(queryParam.getPageNo(), queryParam.getPageSize());
        int count = sysUserMapper.count(params);
        List<SysUser> list = sysUserMapper.page(params);
        return new Page<>(count, list);
    }

    @Override
    public SysUser load(Long id) {
        return sysUserMapper.load(id);
    }

    @Override
    public int update(SysUser sysUser) {
        return sysUserMapper.update(sysUser);
    }

    @Override
    public int delete(Long id) {
        return sysUserMapper.delete(id);
    }

    @Override
    public SysUser getUserInfo(String loginName, String type) {
        Map<String, Object> hashMap = new HashMap<>();
        hashMap.put("username", loginName);
        hashMap.put("type", type);
        return sysUserMapper.getUserInfo(hashMap);
    }
}
