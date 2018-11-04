package com.frames.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * description: 加载配置文件
 * author: Smile
 * date: 2017/4/8
 */
// 获去方式二
@Component
@ConfigurationProperties(prefix="com.prop")
public class MyProperties {

    private static MyProperties myProperties ;

    private MyProperties(){}

    public static MyProperties getInstance(){
        if(null == myProperties){
            synchronized (MyProperties.class){
                if(null == myProperties){
                    myProperties = new MyProperties();
                }
            }
        }
        return myProperties;
    }

    private String  cnName;

    public String getCnName() {
        return cnName;
    }

    public void setCnName(String cnName) {
        this.cnName = cnName;
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
