package com.earth.shiro.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

@Configuration
public class ShiroConfig {


    @Bean
    public ShiroDialect getShiroDialect() {
        return new ShiroDialect();
    }

    @Bean
    public DefaultWebSessionManager getDefaultWebSessionManager() {
        DefaultWebSessionManager manager = new DefaultWebSessionManager();
        // 默认时长 1800000 毫秒
        System.out.println("DefaultWebSessionManager 默认时长 : " + manager.getGlobalSessionTimeout());
        manager.setGlobalSessionTimeout(5 * 1000);
        return manager;
    }

    // ------ 支持shiro注解
    @Bean
    public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator proxyCreator = new DefaultAdvisorAutoProxyCreator();
        proxyCreator.setProxyTargetClass(Boolean.TRUE);
        return proxyCreator;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor getAuthorizationAttributeSourceAdvisor(DefaultWebSecurityManager defaultWebSecurityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(defaultWebSecurityManager);
        return advisor;
    }
    // ------ 支持shiro注解


    @Bean
    public MyRealm getMyRealm(HashedCredentialsMatcher hashedCredentialsMatcher) {
        MyRealm myRealm = new MyRealm();
        myRealm.setCredentialsMatcher(hashedCredentialsMatcher);
        return myRealm;
    }


    @Bean
    public HashedCredentialsMatcher getHashedCredentialsMatcher() {
        // HashedCredentialsMatcher 用于指定加密规则
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        // 使用MD5的加密方式
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        // 使用MD5加密的次数 加密再加密 MD5再MD5
        hashedCredentialsMatcher.setHashIterations(1);
        return hashedCredentialsMatcher;
    }

    // 在ShiroConfig.java中配置机遇cookie的rememberMe管理器
    @Bean
    public CookieRememberMeManager cookieRememberMeManager() {
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        // 需要给cookie设置名字,shiro才能识别对应的cookie
        SimpleCookie cookie = new SimpleCookie("rememberMe");
        cookie.setMaxAge(30*24*60*60);
        cookieRememberMeManager.setCookie(cookie);
        return cookieRememberMeManager;
    }


    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManager(MyRealm MyRealm) {
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(MyRealm);
        defaultWebSecurityManager.setSessionManager(getDefaultWebSessionManager());
        // 设置rememberMe管理器
        defaultWebSecurityManager.setRememberMeManager(cookieRememberMeManager());
        return defaultWebSecurityManager;
    }

    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager defaultWebSecurityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);
        HashMap<String, String> filters = new HashMap<>();
        // 设置 shiro的权限拦截
        // anon 不需要登录也能访问的权限
        // authc 认证用户可以访问
        // user 使用rememberMe的用户访问(表示记住我可以访问)
        // perms 对应权限可以访问 能够添加参数判断 perms[sys:c:add]
        // roles 对应的角色可以访问
        filters.put("/", "anon");
        filters.put("/login.html", "anon");
        filters.put("/index.html", "authc");
        filters.put("/regist.html", "anon");
        filters.put("/user/login", "anon");
        filters.put("/user/regist", "anon");
        filters.put("/static/**", "anon");
        filters.put("/**", "authc");
        filters.put("/c_add.html", "perms[sys:c:save]");

        // shiro框架的Filter 已经做了此logout功能,删除对应的session , 只要访问此路径即可
        filters.put("/logout", "logout");

        filters.put("/test", "perms[url]");


        shiroFilterFactoryBean.setLoginUrl("/login.html");
        // 设置未授权的页面路径,权限不足跳此页面
        shiroFilterFactoryBean.setUnauthorizedUrl("/login.html");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filters);
        return shiroFilterFactoryBean;
    }


}
