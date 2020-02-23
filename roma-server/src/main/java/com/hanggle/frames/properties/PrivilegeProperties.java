package com.hanggle.frames.properties;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: hanggle
 * @date: 2019/7/27
 */
@Slf4j
@Data
@Component
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

    @PostConstruct
    public void init() {
        log.info("PrivilegeProperties:{}", this.toString());
    }
}
