package com.example.primordialshiro.config;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Shiro 的配置主要配置 3 个 Bean ：
 * （1）首先需要提供一个 Realm 的实例。
 * （2）需要配置一个 SecurityManager，在 SecurityManager 中配置 Realm。
 * （3）配置一个 ShiroFilterFactoryBean ，在 ShiroFilterFactoryBean 中指定路径拦截规则等。
 * （4）配置登录和测试接口。
 */
@Configuration
public class ShiroConfig {

    @Bean
    MyRealm myRealm() {
        return new MyRealm();
    }

    @Bean
    SecurityManager securityManager() {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(myRealm());
        return manager;
    }
    @Bean
    ShiroFilterFactoryBean shiroFilterFactoryBean() {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(securityManager());//setSecurityManager 表示指定 SecurityManager。
        bean.setLoginUrl("/login");//setLoginUrl 表示指定登录页面。
        bean.setSuccessUrl("/index");//setSuccessUrl 表示指定登录成功页面。
        //接下来的 Map 中配置了路径拦截规则，注意，要有序。
        Map<String, String> map = new LinkedHashMap<>();
        //匿名访问
        map.put("/doLogin", "anon");
        //登录后访问
        map.put("/**", "authc");
        //定义拦截规则
        bean.setFilterChainDefinitionMap(map);
        return bean;
    }



}
