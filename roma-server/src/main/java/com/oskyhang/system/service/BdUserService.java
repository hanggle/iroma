package com.oskyhang.system.service;

import com.hanggle.frames.base.Page;
import com.oskyhang.system.dto.MenuQueryParam;
import com.oskyhang.system.dto.MenuTreeDto;
import com.oskyhang.system.dto.QueryParam;
import com.oskyhang.system.entity.BdUser;

import java.util.List;

/**
 * @description:
 * @author: hanggle
 * @date: 2018/12/23
 */
public interface BdUserService {
    void insert(BdUser bdUser);

    List<BdUser> list(QueryParam queryParam);

    Page<BdUser> page(QueryParam queryParam);

    /**
     * 查询菜单
     * @param id id
     * @return menu
     */
    BdUser load(Long id);

    /**
     *  更新菜单
     * @param bdUser menu
     * @return menu
     */
    int update(BdUser bdUser);

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
    BdUser getUserInfo(String loginName, String type);
}
