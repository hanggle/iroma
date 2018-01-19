package com.common;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * description: 加载配置文件
 * author: Smile
 * date: 2017/4/8
 */
// 获去方式二
@Component
@ConfigurationProperties(prefix="com.neo")
public class MyProperties {

    private String  cnName;

    private String enName;

    public String getCnName() {
        return cnName;
    }

    public void setCnName(String cnName) {
        this.cnName = cnName;
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String engName) {
        this.enName = engName;
    }

}
/*
//获去方式一
@Component
public class MyProperties {
    @Value("${com.neo.title}")
    private String  cnName;

    @Value("${com.neo.description}")
    private String enName;

    public String getCnName() {
        return cnName;
    }

    public void setCnName(String cnName) {
        this.cnName = cnName;
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String engName) {
        this.enName = engName;
    }

}
*/
