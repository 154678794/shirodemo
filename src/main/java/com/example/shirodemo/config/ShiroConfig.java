package com.example.shirodemo.config;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * TODO
 *
 * @author admin
 * @version 1.0
 * @date 2021/2/23 16:31
 */
@Configuration
public class ShiroConfig {
    //ShiroFilterFactoryBean
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        //设置安全管理器
        bean.setSecurityManager(defaultWebSecurityManager);
        //anon:
        //authc:
        //perms:拥有对某个资源的权限才可以访问
        //role:拥有某个角色的权限可以访问

        //添加shiro内置过滤器，拦截
        Map<String,String> filterMap = new LinkedHashMap<>();
        filterMap.put("/user/add","anon");
        //filterMap.put("/user/add","perms[user:add]");
        filterMap.put("/user/update","authc");

        bean.setFilterChainDefinitionMap(filterMap);

        //设置登录请求,跳转失败时跳到登陆的url
        bean.setLoginUrl("/login.html");
        //设置未授权，跳转界面
        bean.setUnauthorizedUrl("/noauth");

        return bean;
    }

    //DefaultWebSecurityManager
    @Bean(name="securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm);
        return securityManager;
    }

    //创建realm对象，需要自定义类
    @Bean
    public UserRealm userRealm(){
        return new UserRealm();
    }
}
