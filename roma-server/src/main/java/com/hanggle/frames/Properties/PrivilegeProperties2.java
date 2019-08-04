package com.hanggle.frames.Properties;

import lombok.Data;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @description:
 * @author: hanggle
 * @date: 2019/7/27
 */
@Data
//@ConfigurationProperties(prefix = "privilege", ignoreUnknownFields = false)
public class PrivilegeProperties2 {
    private List<Url> commonAnonymous = new ArrayList();
    private List<PrivilegeProperties2.Url> anonymous = new ArrayList();
    private List<PrivilegeProperties2.Url> commonAuthentication = new ArrayList();
    private List<PrivilegeProperties2.Url> authentication = new ArrayList();
    private List<PrivilegeProperties2.AuthUrl> commonAuthorization = new ArrayList();
    private List<PrivilegeProperties2.AuthUrl> authorization = new ArrayList();

    private void appendUrls(List<PrivilegeProperties2.Url> resources, StringBuilder sb) {
        if (null != resources && !resources.isEmpty()) {
            Iterator var3 = resources.iterator();

            while (var3.hasNext()) {
                PrivilegeProperties2.Url url = (PrivilegeProperties2.Url) var3.next();
                sb.append("    ").append("  - path:").append(url.getPath()).append("\n");
                sb.append("    ").append("    methods:").append(url.getMethods()).append("\n");
            }

        }
    }

    private void appendAuthUrls(List<PrivilegeProperties2.AuthUrl> resources, StringBuilder sb) {
        if (null != resources && !resources.isEmpty()) {
            Iterator var3 = resources.iterator();

            while (true) {
                PrivilegeProperties2.AuthUrl url;
                do {
                    if (!var3.hasNext()) {
                        return;
                    }

                    url = (PrivilegeProperties2.AuthUrl) var3.next();
                    sb.append("    ").append("  - path:").append(url.getPath()).append("\n");
                    sb.append("    ").append("    methods:").append(url.getMethods()).append("\n");
                } while (url.getPrivileges().isEmpty());

                sb.append("    ").append("    privileges:\n");
                Iterator var5 = url.getPrivileges().iterator();

                while (var5.hasNext()) {
                    EtaPermission permission = (EtaPermission) var5.next();
                    sb.append("          - ").append(permission.getCode()).append("\n");
                }
            }
        }
    }

    @Data
    public class AuthUrl extends PrivilegeProperties2.Url {
        private List<EtaPermission> privileges = new EtaPermissionList();

        public AuthUrl() {
        }

        public List<EtaPermission> getPrivileges() {
            return this.privileges;
        }
    }

    @Data
    public class Url {
        private String path;
        private String methods;
    }
}