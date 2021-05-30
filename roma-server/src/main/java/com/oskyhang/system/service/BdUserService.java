package com.oskyhang.system.service;

import com.hanggle.frames.base.Page;
import com.oskyhang.system.dto.QueryParam;
import com.oskyhang.system.entity.SysUser;

import java.util.List;

/**
 * @description:
 * @author: hanggle
 * @date: 2018/12/23
 */
public interface BdUserService {
    Long insert(SysUser sysUser);

    List<SysUser> list(QueryParam queryParam);

    Page<SysUser> page(QueryParam queryParam);

    /**
     * 查询菜单
     * @param id id
     * @return menu
     */
    SysUser load(Long id);

    /**
     *  更新菜单
     * @param sysUser menu
     * @return menu
     */
    int update(SysUser sysUser);

    /**
     *  删除菜单
     * @param id id
     * @return menu
     */
    int delete(Long id);

    /**
     * 获去登录用户信息
     * @param loginName 用户名、邮箱、手机号
     * @param type
     * @return
     */
    SysUser getUserInfo(String loginName, String type);
}
