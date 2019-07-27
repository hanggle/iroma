package com.hanggle.frames.Properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: hanggle
 * @date: 2019/1/22
 */
@Data
@Component
@ConfigurationProperties(prefix="shiro.redis")
public class ShiroRedisConfig {

    private String host;

    private int port;

    private int timeout;

    private String password;

}
