package com.oskyhang.system.service.impl;

import com.oskyhang.system.entity.BdMenu;
import com.oskyhang.system.mapper.BdMenuMapper;
import com.oskyhang.system.service.BdMenuService;
import com.oskyhang.utils.HanggleUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Description:
 * User: z.hang <br/>
 * Date: 2018-01-14 <br/>
 * Time: 18:25 <br/>
 */
@Service
public class BdMenuServiceImpl implements BdMenuService {

    @Autowired
    private BdMenuMapper bdMenuMapper;

    @Override
    public int insert(BdMenu bdMenu) {
        bdMenu.setId(HanggleUtil.getUUID());
        return bdMenuMapper.insertSelective(bdMenu);
    }

    @Override
    public List<BdMenu> selectMenuList(String orderCode) {

        return bdMenuMapper.selectMenuList(orderCode);
    }

    @Override
    public BdMenu selectByPrimaryKey(String id) {

        return bdMenuMapper.selectByPrimaryKey(id);
    }
}
