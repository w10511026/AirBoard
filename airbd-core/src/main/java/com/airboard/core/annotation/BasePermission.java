package com.airboard.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description 收集权限菜单自定义注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface BasePermission {
    //权限名称
    String name();
    //权限标识
    String value();
    //父类标识
    String parent();
    //是否是菜单
    boolean isMenu() default false;

}
