package com.airboard.web.config;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        factoryBean.setSecurityManager(securityManager);
        factoryBean.setLoginUrl("/");
        //自定义url过滤规则 <!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
        Map<String, String> filterRuleMap = new HashMap<>(10);
        filterRuleMap.put("/druid/**", "anon");
        filterRuleMap.put("/swagger-ui.html", "anon");
        filterRuleMap.put("/swagger-resources", "anon");
        filterRuleMap.put("/v2/api-docs", "anon");
        filterRuleMap.put("/webjars/springfox-swagger-ui/**", "anon");
        filterRuleMap.put("/css/**", "anon");
        filterRuleMap.put("/fonts/**", "anon");
        filterRuleMap.put("/img/**", "anon");
        filterRuleMap.put("/js/**", "anon");
        filterRuleMap.put("/login", "anon");
        //过滤链定义，从上向下顺序执行，一般将 /** 放在最为下边
        filterRuleMap.put("/**", "authc");
        factoryBean.setFilterChainDefinitionMap(filterRuleMap);
        return factoryBean;
    }

    @Bean
    public DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(iShiroRealm());
        return securityManager;
    }

    @Bean
    public IShiroRealm iShiroRealm() {
        IShiroRealm iShiroRealm = new IShiroRealm();
        iShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return new IShiroRealm();
    }

    /**
     * 凭证匹配器
     * （由于我们的密码校验交给Shiro的SimpleAuthenticationInfo进行处理了
     * 所以我们需要修改下doGetAuthenticationInfo中的代码;
     * ）
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        //散列算法:这里使用MD5算法
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        //散列的次数，比如散列两次，相当于 md5(md5(""))
        hashedCredentialsMatcher.setHashIterations(2);
        return hashedCredentialsMatcher;
    }

    /**
     * 开启shiro aop注解支持.
     * 使用代理方式;所以需要开启代码支持;
     * 主要是用于方法名上@RequiresPermissions注解的使用
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }
}