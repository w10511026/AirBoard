package com.airboard.hplus.config;

import com.jagregory.shiro.freemarker.ShiroTags;
import freemarker.template.Configuration;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Description 页面上配置shiro标签
 */
@Component
public class ShiroFreemarkerTagConfig implements InitializingBean {

    @Autowired
    private Configuration configuration;

    @Override
    public void afterPropertiesSet() {
        configuration.setSharedVariable("shiro", new ShiroTags());
    }
}
