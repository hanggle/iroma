package com.hanggle.frames.Properties;

import lombok.Data;
import org.apache.shiro.authz.Permission;

/**
 * @description:
 * @author: hanggle
 * @date: 2019/7/27
 */
public class EtaPermission {

    private static final long serialVersionUID = 7010268791859328751L;
    String code;

    public EtaPermission(String code) {
        this.code = code;
    }

    public String toString() {
        return this.code;
    }

    public boolean implies(Permission p) {
        if (!(p instanceof EtaPermission)) {
            return false;
        } else {
            String that = ((EtaPermission)p).code;
            return this.code.equals(that) || this.code.startsWith(that.concat("."));
        }
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            EtaPermission that = (EtaPermission)o;
            return this.code != null ? this.code.equals(that.code) : that.code == null;
        } else {
            return false;
        }
    }

    public int hashCode() {
        return this.code != null ? this.code.hashCode() : 0;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
