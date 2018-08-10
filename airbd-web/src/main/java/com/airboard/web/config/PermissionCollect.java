package com.airboard.web.config;

import com.airboard.core.annotation.BasePermission;
import com.airboard.core.enums.PermissionTypeEnum;
import com.airboard.core.model.system.SysPermission;
import com.airboard.core.service.system.SysPermissionService;
import com.airboard.core.util.IdWorker;
import com.airboard.core.util.NumberUtils;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.IdGenerator;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.Method;

/**
 * @Description 启动时收集所有的权限菜单
 */
@Log4j2
@Component
public class PermissionCollect implements BeanPostProcessor {

    @Autowired
    private SysPermissionService sysPermissionService;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Method[] methods = ReflectionUtils.getAllDeclaredMethods(bean.getClass());
        if (ArrayUtils.isNotEmpty(methods)) {
            for (Method method : methods) {
                BasePermission basePermission = AnnotationUtils.findAnnotation(method, BasePermission.class);
                if (null != basePermission) {
                    String permission = basePermission.value();
                    Integer count = sysPermissionService.countByPermission(permission);
                    if (NumberUtils.isEmpty(count)) {
                        RequestMapping clazzUrl = AnnotationUtils.findAnnotation(bean.getClass(), RequestMapping.class);
                        RequestMapping methodUrl = AnnotationUtils.findAnnotation(method, RequestMapping.class);
                        String requestUrl = "";
                        if (null != clazzUrl && null != methodUrl) {
                            requestUrl = clazzUrl.value()[0] + methodUrl.value()[0];
                        }
                        SysPermission sysPermission = new SysPermission();
                        sysPermission.setName(basePermission.name());
                        sysPermission.setPermission(permission);
                        sysPermission.setParent(basePermission.parent());
                        if (basePermission.isMenu()) {
                            sysPermission.setType(PermissionTypeEnum.MENU.type);
                        } else {
                            sysPermission.setType(PermissionTypeEnum.PERMISSION.type);
                        }
                        sysPermission.setAccessUrl(requestUrl);
                        sysPermissionService.insert(sysPermission);
                    }
                }
            }
        }
        return bean;
    }

}
