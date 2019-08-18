package com.oskyhang.system.service.impl;

import com.hanggle.frames.properties.ShiroRedisProperties;
import com.oskyhang.system.controller.BaseTestController;
import com.oskyhang.system.dto.MenuTreeDto;
import com.oskyhang.system.service.BdMenuService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @description:
 * @author: hanggle
 * @date: 2018/11/25
 */

public class BdMenuServiceImplTest extends BaseTestController {
    @Autowired
    private BdMenuService bdMenuService;
    @Autowired
    private ShiroRedisProperties shiroRedisProperties;

    @Test
    public void selectMenuTree() {
        MenuTreeDto menuTreeDtos = bdMenuService.selectMenuTree();
        System.out.println(menuTreeDtos);
        System.out.println(shiroRedisProperties.getHost());
    }
}