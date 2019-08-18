package com.hanggle.frames.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: hanggle
 * @date: 2019/7/27
 */
@Data
@ConfigurationProperties(prefix = "privilege", ignoreUnknownFields = false)
public class PrivilegeProperties {

    private List<Url> anonymous = new ArrayList<>();
    private List<Url> authentication = new ArrayList();
    private List<AuthUrl> authorization = new ArrayList<>();

    @Data
    public static class Url{
        private String path;
        private String methods;
    }

    @Data
    public static class AuthUrl {
        private String path;
        private String methods;
        private List<String> privileges;
    }
}
