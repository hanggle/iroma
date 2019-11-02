package com.hanggle.frames.shiro;

import com.hanggle.utils.HanggleUtil;
import com.oskyhang.system.entity.BdPermission;
import com.oskyhang.system.entity.BdRole;
import com.oskyhang.system.entity.BdUser;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;
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

        String username = (String) principals.getPrimaryPrincipal();
        List<BdRole> roles = shiroService.selectRoleByUser(username);
        Set<String> rolesSet = roles.stream().map(BdRole::getRole).collect(Collectors.toSet());
        authorizationInfo.setRoles(rolesSet);

        List<Long> roleIds = roles.stream().map(BdRole::getId).collect(Collectors.toList());
        List<BdPermission> bdPermissions = shiroService.selectPermission(roleIds);
        Set<String> permissions = bdPermissions.stream().map(BdPermission::getUrl).collect(Collectors.toSet());
        authorizationInfo.setStringPermissions(permissions);

        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        log.debug("账号认证-->MyShiroRealm.doGetAuthenticationInfo()");
        //获取用户账号
        String username = String.valueOf(token.getPrincipal());
        BdUser bdUser = shiroService.getUserInfoByUsername(username);
        String password = bdUser.getPassword();

        // 获取盐值，即用户名
        ByteSource salt = ByteSource.Util.bytes(HanggleUtil.MD5(username));
        if (password != null) {
            AuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                    username,   //认证通过后，存放在session,一般存放user对象
                    password,   //用户数据库中的密码
                    salt,
                    getName());    //返回Realm名
            return authenticationInfo;
        }
        return null;
    }
}
