package com.oskyhang.utils;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ZhUtilTest {
    private static Logger logger = LoggerFactory.getLogger(ZhUtilTest.class);
    @Test
    public void getUUID() {
       logger.debug(ZhUtil.getUUID());
    }
}