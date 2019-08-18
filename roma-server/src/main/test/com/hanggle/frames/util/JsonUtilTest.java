package com.hanggle.frames.util;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import org.junit.Test;

import java.util.Map;

/**
 * description: <br/>
 * author: zh <br/>
 * date: 2018/4/13 <br/>
 */
public class JsonUtilTest {
    @Test
    public void json2str() throws Exception {
    }

    @Test
    public void obj2Json() throws Exception {
    }

    @Test
    public void json2Class() throws Exception {

        Map<String, Object> param = Maps.newHashMap();
        param.put("autohomeId", "212341");
        String s = JSON.toJSONString(param);
        System.out.println(s);
    }

}
class Uss{
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;
}
