package com.oskyhang.system.service.impl;

import com.hanggle.frames.shiro.ShiroConfig;
import com.hanggle.utils.CommonUtil;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.Test;

import static org.junit.Assert.*;

public class BdUserServiceImplTest {



    @Test
    public void insert() {
        String password ="admin";
        String algorithmName = ShiroConfig.algorithmName;
        String salt = CommonUtil.MD5("admin");
        int hashIteration = ShiroConfig.hashIteration;
        SimpleHash simpleHash = new SimpleHash(algorithmName, password, salt, hashIteration);

        System.out.println(simpleHash);
    }
}