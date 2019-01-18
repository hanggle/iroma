package com.hanggle.frames.shiro;

import com.oskyhang.system.entity.BdPermission;
import com.oskyhang.system.entity.BdRole;
import com.oskyhang.system.entity.BdUser;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * @description:
 * @author: hanggle
 * @date: 2019/1/15
 */
@Service
public class ShiroService {

    public String getPasswordByUsername(String username){
        switch (username){
            case "liming":
                return "123";
            case "hanli":
                return "456";
            default:
                return null;
        }
    }
    public List<BdRole> selectRoleByUser(BdUser bdUser){
        BdPermission bdPermission = new BdPermission();
        bdPermission.setUrl("/user/info");

        BdRole bdRole = new BdRole();
        bdRole.setRole("admin");

        return Arrays.asList(bdRole);
    }

    public List<BdPermission> selectPermission(List<Long> roleIds){
        BdPermission bdPermission = new BdPermission();
        bdPermission.setUrl("/user/info");

        return Arrays.asList(bdPermission);
    }
}
