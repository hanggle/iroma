package com.oskyhang.system.service.impl;

import com.hanggle.frames.Properties.ShiroRedisConfig;
import com.oskyhang.system.controller.BaseTestController;
import com.oskyhang.system.dto.MenuTreeDto;
import com.oskyhang.system.service.BdMenuService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @description:
 * @author: hanggle
 * @date: 2018/11/25
 */

public class BdMenuServiceImplTest extends BaseTestController {
    @Autowired
    private BdMenuService bdMenuService;
    @Autowired
    private ShiroRedisConfig shiroRedisConfig;

    @Test
    public void selectMenuTree() {
        MenuTreeDto menuTreeDtos = bdMenuService.selectMenuTree();
        System.out.println(menuTreeDtos);
        System.out.println(shiroRedisConfig.getHost());
    }
}