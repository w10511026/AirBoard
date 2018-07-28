package com.airboard.core.annotation;

import java.lang.annotation.*;

/**
 * @Description
 * @Author <a href="mailto:wangshuo@ebnew.com">Wangshuo</a>
 * @Date 2018/7/28
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
@Documented
public @interface RedisCache {

    /**
     * @Description: 数据返回类型
     */
    Class type();

    /**
     * @Description: 数据缓存时间单位s秒, 默认10分钟
     */
    int cacheTime() default 600;

}
