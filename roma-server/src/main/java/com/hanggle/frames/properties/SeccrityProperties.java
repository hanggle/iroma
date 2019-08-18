package com.hanggle.frames.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: hanggle
 * @date: 2019/1/15
 */
@Data
@Component
@ConfigurationProperties(prefix = "roma.security")
public class SeccrityProperties {
    private String logout;
}
