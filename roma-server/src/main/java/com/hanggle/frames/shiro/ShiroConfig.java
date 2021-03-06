package com.hanggle.frames.shiro;

import com.hanggle.frames.properties.PrivilegeProperties;
import com.hanggle.frames.properties.SecurityProperties;
import com.hanggle.frames.properties.ShiroRedisProperties;
import com.hanggle.frames.shiro.MySessionManager;
import com.hanggle.frames.shiro.MyShiroRealm;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.session.mgt.DefaultSessionManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: hanggle
 * @date: 2019/1/15
 */
@Slf4j
@Configuration
@EnableConfigurationProperties({PrivilegeProperties.class})
public class ShiroConfig {
    //散列的次数，比如散列两次，相当于 md5(md5(""));
    public final static Integer hashIteration = 2;
    //散列算法:这里使用MD5算法
    public final static String algorithmName = "md5";

    @Autowired
    private ShiroRedisProperties shiroRedisProperties;
    @Autowired
    private PrivilegeProperties privilegeProperties;
    @Autowired
    private SecurityProperties securityProperties;

    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager securityManager) {
        log.debug("ShiroConfig[]shiroFilterFactoryBean");
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //拦截器.
        Map<String,String> filterChainDefinitionMap = new LinkedHashMap<String,String>();
        // 配置不会被拦截的链接 顺序判断
        /*filterChainDefinitionMap.put("/api/user/login/login", "anon");
        filterChainDefinitionMap.put("/test3/test", "anon");
        //配置退出 过滤器,其中的具体的退出代码Shiro已经替我们实现了*/
        filterChainDefinitionMap.put(securityProperties.getLogout(), "logout");
        //<!-- 过滤链定义，从上向下顺序执行，一般将/**放在最为下边 -->:这是一个坑呢，一不小心代码就不好使了;
        //<!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
        filterChainDefinitionMap= initFilterMap();
//        filterChainDefinitionMap.put("/**", "authc");

        // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
        shiroFilterFactoryBean.setLoginUrl("/api/user/login/notLogin");
        // 登录成功后要跳转的链接
        shiroFilterFactoryBean.setSuccessUrl("/index");

        //未授权界面;
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    public Map<String,String> initFilterMap() {
        Map<String,String> filterMap = new LinkedHashMap<String,String>();
        List<PrivilegeProperties.Url> anonymous = privilegeProperties.getAnonymous();
        List<PrivilegeProperties.Url> authentication = privilegeProperties.getAuthentication();
        List<PrivilegeProperties.AuthUrl> authorization = privilegeProperties.getAuthorization();

        for (PrivilegeProperties.Url url : anonymous) {
            filterMap.put(url.getPath(), "anon");
        }

        for (PrivilegeProperties.Url url : authentication) {
            filterMap.put(url.getPath(), "authc");
        }
        for (PrivilegeProperties.AuthUrl authUrl : authorization) {
            List<String> privileges = authUrl.getPrivileges();
            String[] privilegesStr = privileges.toArray(new String[privileges.size()]);
            filterMap.put(authUrl.getPath(), "authc,roles" + Arrays.toString(privilegesStr));
        }
        log.info("shiroFilter.filterMap:{}", filterMap);
        return filterMap;
    }

    /**
     * 凭证匹配器
     * （由于我们的密码校验交给Shiro的SimpleAuthenticationInfo进行处理了）
     * @return
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName(algorithmName);
        hashedCredentialsMatcher.setHashIterations(hashIteration);
        return hashedCredentialsMatcher;
    }

    @Bean
    public DefaultWebSecurityManager securityManager(){
        DefaultWebSecurityManager securityManager =  new DefaultWebSecurityManager();
        //设置realm
        securityManager.setRealm(myShiroRealm());
        // 自定义sessionManager
        securityManager.setSessionManager(sessionManager());
        securityManager.setCacheManager(cacheManager());
        return securityManager;
    }

    /**
     * 自定义的Realm
     */
    @Bean(name = "myShiroRealm")
    public MyShiroRealm myShiroRealm(){
        MyShiroRealm myShiroRealm = new MyShiroRealm();
        myShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return myShiroRealm;
    }
    /**
     * cacheManager 缓存 redis实现
     * 使用的是shiro-redis开源插件
     *
     * @return
     */
    public RedisCacheManager cacheManager() {
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager());
        return redisCacheManager;
    }
    /**
     * 配置shiro redisManager
     * 使用的是shiro-redis开源插件
     *
     * @return
     */
    public RedisManager redisManager() {
        RedisManager redisManager = new RedisManager();
        redisManager.setHost(shiroRedisProperties.getHost());
        redisManager.setPort(shiroRedisProperties.getPort());
        redisManager.setExpire(1800);// 配置缓存过期时间
        redisManager.setTimeout(shiroRedisProperties.getTimeout());
        redisManager.setPassword(shiroRedisProperties.getPassword());
        return redisManager;
    }

    /**
     * Session Manager
     * 使用的是shiro-redis开源插件
     */
    @Bean
    public DefaultWebSessionManager sessionManager() {
        MySessionManager sessionManager = new MySessionManager();
        sessionManager.setSessionDAO(redisSessionDAO());
        return sessionManager;
    }

    /**
     * RedisSessionDAO shiro sessionDao层的实现 通过redis
     * 使用的是shiro-redis开源插件
     */
    @Bean
    public RedisSessionDAO redisSessionDAO() {
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        redisSessionDAO.setRedisManager(redisManager());
        return redisSessionDAO;
    }



    /**
     * 会话管理类 禁用session
     * @return
     */
    @Bean
    public DefaultSessionManager defaultSessionManager(){
        log.info("ShiroConfig.getDefaultSessionManager()");
        DefaultSessionManager manager = new DefaultSessionManager();
        manager.setSessionValidationSchedulerEnabled(false);
        return manager;
    }
}
