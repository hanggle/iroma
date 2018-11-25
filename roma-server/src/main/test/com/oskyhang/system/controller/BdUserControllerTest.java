package com.oskyhang.system.controller;

import com.hanggle.frames.util.RedisUtils;
import com.hanggle.frames.util.SpringContextUtils;
import com.hanggle.utils.HanggleUtil;
import com.oskyhang.system.entity.BdMenu;
import com.oskyhang.system.mapper.BdMenuMapper;
import org.junit.Test;

/**
 * description: <br/>
 * author: zh <br/>
 * date: 2018/4/13 <br/>
 */
public class BdUserControllerTest extends BaseTestController{
    @Test
    public void test1() {
        BdMenuMapper bdUserDao = SpringContextUtils.getBeanByType(BdMenuMapper.class);
        BdMenu bdMenu = bdUserDao.load(1000000000000000003L);
        System.out.println(bdMenu);
    }
    @Test
    public void test2() {
        System.out.println(RedisUtils.getLock("lockTest", HanggleUtil.getUUID(), "10"));
    }

}
