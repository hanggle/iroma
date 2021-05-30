package com.oskyhang.system.service.impl;

import com.google.common.base.Objects;
import com.hanggle.frames.annotation.RomaCache;
import com.hanggle.frames.util.IdUtil;
import com.oskyhang.system.dto.LoginUser;
import com.oskyhang.system.dto.MenuQueryParam;
import com.oskyhang.system.dto.SelectDto;
import com.oskyhang.system.dto.MenuTreeDto;
import com.oskyhang.system.entity.SysMenu;
import com.oskyhang.system.mapper.SysMenuMapper;
import com.oskyhang.system.service.BdMenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static com.hanggle.utils.Arguments.notNull;

/**
 * Description: 菜单接口 <br/>
 * @author : z.hang <br/>
 * Date: 2018-01-14 <br/>
 * Time: 18:25 <br/>
 */
@Slf4j
@Service
public class BdMenuServiceImpl implements BdMenuService {

    @Autowired
    private SysMenuMapper bdMenuDao;
    private final String cache_load = "test_";

    @Override
    public int delete(Long id) {
        return bdMenuDao.delete(id);
    }

    @Override
    public void insertAndUpdate(SysMenu sysMenu) {
        SysMenu parentMenu = bdMenuDao.load(sysMenu.getParentId());
        sysMenu.setLevel(parentMenu.getLevel()+1);
        if (notNull(sysMenu.getId())) {
            update(sysMenu);
        } else {
            sysMenu.setId(IdUtil.getNextId());
            bdMenuDao.insert(sysMenu);
        }
    }

    @Override
    public int update(SysMenu sysMenu) {
        return bdMenuDao.update(sysMenu);
    }

    @Override
    public List<SelectDto> menuSelect(MenuQueryParam menuQueryParam) {
        return bdMenuDao.menuSelect();
    }

    @RomaCache(key = {"#menuQueryParam", "#loginUser"}, prefix = cache_load)
    @Override
    public List<SysMenu> list(MenuQueryParam menuQueryParam, LoginUser loginUser) {
        Map<String, Object> params = new HashMap<>(16);
        params.put("orderBy", "level, order_code");
        return bdMenuDao.list(params);
    }

    @Override
    public MenuTreeDto selectMenuTree() {
        Map<String, Object> params = new HashMap<>(16);
        List<SysMenu> sysMenus = bdMenuDao.list(params);
        // 根菜单
        MenuTreeDto menuTreeDto = new MenuTreeDto();
        menuTreeDto.setId(sysMenus.get(0).getId());
        menuTreeDto.setLabel(sysMenus.get(0).getName());
        menuTreeDto.setLevel(sysMenus.get(0).getLevel());
        return getChildMenu(menuTreeDto, sysMenus);
    }
    private MenuTreeDto getChildMenu(MenuTreeDto menuTreeDto, List<SysMenu> sysMenus) {
        List<SysMenu> menuList = sysMenus.stream().filter(obj -> Objects.equal(menuTreeDto.getId(), obj.getParentId())).collect(Collectors.toList());
        List<MenuTreeDto> treeDtos = menuList.stream().map(MenuTreeDto::new).collect(Collectors.toList());
        menuTreeDto.setChildren(treeDtos);
        for (MenuTreeDto menuTreeDto1 : treeDtos) {
            getChildMenu(menuTreeDto1, sysMenus);
        }
        return menuTreeDto;
    }

    @RomaCache(key = "#id", prefix= cache_load)
    @Override
    public SysMenu load(Long id) {
        log.info("111111");

        return bdMenuDao.load(id);
    }
}
