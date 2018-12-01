package com.oskyhang.system.service.impl;

import com.google.common.base.Objects;
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

/**
 * Description: 菜单接口 <br/>
 * @author : z.hang <br/>
 * Date: 2018-01-14 <br/>
 * Time: 18:25 <br/>
 */
@Service
public class BdMenuServiceImpl implements BdMenuService {

    @Autowired
    private BdMenuMapper bdMenuMapper;

    @Override
    public int delete(Long id) {
        return bdMenuMapper.delete(id);
    }

    @Override
    public int insert(BdMenu bdMenu) {
        bdMenu.setId(1L);
        return bdMenuMapper.insert(bdMenu);
    }

    @Override
    public int update(BdMenu bdMenu) {
        return bdMenuMapper.update(bdMenu);
    }

    @Override
    public List<SelectDto> menuSelect(MenuQueryParam menuQueryParam) {
        return bdMenuMapper.menuSelect();
    }

    @Override
    public List<BdMenu> list(MenuQueryParam menuQueryParam) {
        Map<String, Object> params = new HashMap<>(16);
        params.put("orderBy", "level, order_code");
        return bdMenuMapper.list(params);
    }

    @Override
    public MenuTreeDto selectMenuTree() {
        Map<String, Object> params = new HashMap<>(16);
        List<BdMenu> bdMenus = bdMenuMapper.list(params);
        // 根菜单
        MenuTreeDto menuTreeDto = new MenuTreeDto();
        menuTreeDto.setId(bdMenus.get(0).getId());
        menuTreeDto.setLabel(bdMenus.get(0).getName());
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

        return bdMenuMapper.load(id);
    }
}
