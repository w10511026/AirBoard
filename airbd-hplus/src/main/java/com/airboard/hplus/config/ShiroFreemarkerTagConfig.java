package com.airboard.hplus.config;

import com.jagregory.shiro.freemarker.ShiroTags;
import freemarker.template.Configuration;
import freemarker.template.TemplateModelException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Description 页面上配置shiro标签
 */
@Component
public class ShiroFreemarkerTagConfig implements InitializingBean {

    @Value("${server.servlet.server-path}")
    private String serverPath;

    @Autowired
    private Configuration configuration;

    @Override
    public void afterPropertiesSet() throws TemplateModelException {
        configuration.setSharedVariable("shiro", new ShiroTags());
        configuration.setSharedVariable("ctxPath", serverPath);
    }
}
