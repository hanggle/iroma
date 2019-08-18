package com.oskyhang.system.util;

import com.oskyhang.system.controller.BaseTestController;
import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

/**
 * @description:
 * @author: hanggle
 * @date: 2019/4/7
 */
public class CommonTest extends BaseTestController {


    @Test
    public void test(){
        try {
            FileInputStream fs = new FileInputStream(new File("src/main/resources/script/getLock.lua"));
            BufferedInputStream bis = new BufferedInputStream(fs);
            byte[] read = new byte[1024];
            int len = 0;
            while ( (len=bis.read(read)) != -1) {
                String str = new String(read, 0, len);
                System.out.println(str);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
