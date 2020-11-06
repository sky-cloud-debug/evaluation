package com.evaluation.system.util.Shiro;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("getDefaultWebSecurityManager") DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //设置过滤器
        /*
        anon：无需认证
        authc:必须认证
        user：如果使用rememberme功能可以直接访问
        perms：该资源必须得到资源权限才可以访问
        role:该资源必须得到角色权限才可以访问
        */
        Map<String, String> filtermap = new LinkedHashMap<>();
        //分配权限
        //filtermap.put("/add", "perms[add]");
        //iltermap.put("/updata", "perms[updata]");
        //设置拦截
        // 这个必须在前面
        filtermap.put("/", "anon");
        filtermap.put("/api/**", "anon");
        filtermap.put("/css/**", "anon");
        filtermap.put("/excel/**", "anon");
        filtermap.put("/font/**", "anon");
        filtermap.put("/font-awesome-4.7.0/**", "anon");
        filtermap.put("/images/**", "anon");
        filtermap.put("/jq-module/**", "anon");
        filtermap.put("/jquery-3.4.1/**", "anon");
        filtermap.put("/js/**", "anon");
        filtermap.put("/lay/**", "anon");
        filtermap.put("/layui.all.js", "anon");
        filtermap.put("/layui.js", "anon");
        filtermap.put("/main/**", "anon");
        filtermap.put("/login/Login", "anon");
        filtermap.put("/**", "authc");
        shiroFilterFactoryBean.setUnauthorizedUrl("/power/nopower");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filtermap);
//        修改默认的跳转登陆页面
        shiroFilterFactoryBean.setLoginUrl("/login/Login");
        return shiroFilterFactoryBean;
    }

    /*创建DefaultWebSecurityManager*/
    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("getRealm")Realm userRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm);
        securityManager.setRememberMeManager(rememberMeManager());
        return securityManager;
    }

    /*创建Realm*/
    @Bean
    public Realm getRealm() {
        UserRealm customerRealm=new UserRealm();
        //修改凭证校验匹配器
        HashedCredentialsMatcher credentialsMatcher=new HashedCredentialsMatcher();
        //设置加密算法为MD5
        credentialsMatcher.setHashAlgorithmName("MD5");
        //设置散列次数
        credentialsMatcher.setHashIterations(1024);
        customerRealm.setCredentialsMatcher(credentialsMatcher);
        //开启缓存
        customerRealm.setCacheManager(new EhCacheManager());
        customerRealm.setCachingEnabled(true);
        customerRealm.setAuthenticationCachingEnabled(true);//开启认证
        customerRealm.setAuthorizationCacheName("authorizationCache");
        customerRealm.setAuthorizationCachingEnabled(true);//开启权限
        customerRealm.setAuthenticationCacheName("authenticationCache");
        return customerRealm;
    }

    /*配置shiroDialect,用于thymeleaf和shiro配合使用*/
    @Bean
    public ShiroDialect getshiroDialect() {
        return new ShiroDialect();
    }

    //设置cookie对象，记住我功能
    public SimpleCookie rememberMeCookie() {
        //对应前端的checkbox的name=rememberMe
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        //设置cookie的生效时间，两天
        simpleCookie.setMaxAge(172800);
        return simpleCookie;
    }

    //cookie管理对象
    public CookieRememberMeManager rememberMeManager() {
        CookieRememberMeManager manager = new CookieRememberMeManager();
        manager.setCookie(rememberMeCookie());
        manager.setCipherKey("LIZHUANGZHUANG_Y".getBytes());
        return manager;
    }
}
