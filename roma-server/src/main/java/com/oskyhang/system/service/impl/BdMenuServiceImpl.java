package com.oskyhang.system.service.impl;

import com.oskyhang.system.entity.BdMenu;
import com.oskyhang.system.mapper.BdMenuMapper;
import com.oskyhang.system.service.BdMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

        return bdMenuMapper.insertSelective(bdMenu);
    }
}
