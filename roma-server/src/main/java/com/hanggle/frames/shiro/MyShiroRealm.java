package com.hanggle.frames.shiro;

import com.oskyhang.system.entity.BdPermission;
import com.oskyhang.system.entity.BdRole;
import com.oskyhang.system.entity.BdUser;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @description:
 * @author: hanggle
 * @date: 2019/1/15
 */
@Slf4j
public class MyShiroRealm extends AuthorizingRealm {

    @Resource
    public ShiroService shiroService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        log.debug("权限配置-->MyShiroRealm.doGetAuthorizationInfo()");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        BdUser userInfo  = (BdUser) principals.getPrimaryPrincipal();
        List<BdRole> roles = shiroService.selectRoleByUser(userInfo);

        for (BdRole role : roles) {
            authorizationInfo.addRole(role.getRole());
        }

        List<Long> roleIds = roles.stream().map(BdRole::getId).collect(Collectors.toList());
        List<BdPermission> bdPermissions = shiroService.selectPermission(roleIds);
        for (BdPermission p : bdPermissions) {
            authorizationInfo.addStringPermission(p.getUrl());
        }
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //获取用户账号
        String username = String.valueOf(token.getPrincipal());

        String password = shiroService.getPasswordByUsername(username);
        if (password != null) {
            AuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                    username,   //认证通过后，存放在session,一般存放user对象
                    password,   //用户数据库中的密码
                    getName());    //返回Realm名
            return authenticationInfo;
        }
        return null;
    }
}
