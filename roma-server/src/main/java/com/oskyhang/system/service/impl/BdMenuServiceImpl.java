package com.oskyhang.system.service.impl;

import com.oskyhang.system.dto.BdMenuDto;
import com.oskyhang.system.entity.BdMenu;
import com.oskyhang.system.mapper.BdMenuMapper;
import com.oskyhang.system.service.BdMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Description: 菜单接口 <br/>
 * User: z.hang <br/>
 * Date: 2018-01-14 <br/>
 * Time: 18:25 <br/>
 */
@Service
public class BdMenuServiceImpl implements BdMenuService {

    @Autowired
    private BdMenuMapper bdMenuMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return bdMenuMapper.delete(id);
    }

    @Override
    public int insert(BdMenu bdMenu) {
        bdMenu.setId(1L);
        return bdMenuMapper.insert(bdMenu);
    }

    @Override
    public int updateByPrimaryKey(BdMenu bdMenu) {
        return bdMenuMapper.update(bdMenu);
    }

    @Override
    public List<BdMenu> list(BdMenuDto bdMenuDto) {
        Map<String, Object> params = new HashMap<>(16);
        params.put("orderBy", "level, order_code");
        return bdMenuMapper.list(params);
    }

    @Override
    public List<BdMenu> list(Map<String, Object> params) {
        params.put("orderBy", "level, order_code");
        return bdMenuMapper.list(params);
    }

    @Override
    public List<BdMenu> selectMenuTree() {
        return bdMenuMapper.selectMenuTree();
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public BdMenu selectByPrimaryKey(Long id) {

        return bdMenuMapper.load(id);
    }
}
