package com.oskyhang.system.service.impl;

import com.google.common.base.Objects;
import com.hanggle.frames.util.IdUtil;
import com.oskyhang.system.dto.MenuQueryParam;
import com.oskyhang.system.dto.SelectDto;
import com.oskyhang.system.dto.MenuTreeDto;
import com.oskyhang.system.entity.BdMenu;
import com.oskyhang.system.mapper.BdMenuMapper;
import com.oskyhang.system.service.BdMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static com.hanggle.frames.util.Arguments.notNull;

/**
 * Description: 菜单接口 <br/>
 * @author : z.hang <br/>
 * Date: 2018-01-14 <br/>
 * Time: 18:25 <br/>
 */
@Service
public class BdMenuServiceImpl implements BdMenuService {

    @Autowired
    private BdMenuMapper bdMenuDao;

    @Override
    public int delete(Long id) {
        return bdMenuDao.delete(id);
    }

    @Override
    public void insertAndUpdate(BdMenu bdMenu) {
        BdMenu parentMenu = bdMenuDao.load(bdMenu.getParentId());
        bdMenu.setLevel(parentMenu.getLevel()+1);
        if (notNull(bdMenu.getId())) {
            update(bdMenu);
        } else {
            bdMenu.setId(IdUtil.getNextId());
            bdMenuDao.insert(bdMenu);
        }
    }

    @Override
    public int update(BdMenu bdMenu) {
        return bdMenuDao.update(bdMenu);
    }

    @Override
    public List<SelectDto> menuSelect(MenuQueryParam menuQueryParam) {
        return bdMenuDao.menuSelect();
    }

    @Override
    public List<BdMenu> list(MenuQueryParam menuQueryParam) {
        Map<String, Object> params = new HashMap<>(16);
        params.put("orderBy", "level, order_code");
        return bdMenuDao.list(params);
    }

    @Override
    public MenuTreeDto selectMenuTree() {
        Map<String, Object> params = new HashMap<>(16);
        List<BdMenu> bdMenus = bdMenuDao.list(params);
        // 根菜单
        MenuTreeDto menuTreeDto = new MenuTreeDto();
        menuTreeDto.setId(bdMenus.get(0).getId());
        menuTreeDto.setLabel(bdMenus.get(0).getName());
        menuTreeDto.setLevel(bdMenus.get(0).getLevel());
        return getChildMenu(menuTreeDto, bdMenus);
    }
    private MenuTreeDto getChildMenu(MenuTreeDto menuTreeDto, List<BdMenu> bdMenus) {
        List<BdMenu> menuList = bdMenus.stream().filter(obj -> Objects.equal(menuTreeDto.getId(), obj.getParentId())).collect(Collectors.toList());
        List<MenuTreeDto> treeDtos = menuList.stream().map(MenuTreeDto::new).collect(Collectors.toList());
        menuTreeDto.setChildren(treeDtos);
        for (MenuTreeDto menuTreeDto1 : treeDtos) {
            getChildMenu(menuTreeDto1, bdMenus);
        }
        return menuTreeDto;
    }

    @Override
    public BdMenu load(Long id) {

        return bdMenuDao.load(id);
    }
}
