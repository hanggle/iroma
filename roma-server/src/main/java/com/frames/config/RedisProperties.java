package com.frames.config;

import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * description: 加载配置文件
 * author: Smile
 * date: 2017/4/8
 */
// 获去方式二
@Component
@PropertySource("classpath:")
public class RedisProperties {

    private String  host;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }
}
