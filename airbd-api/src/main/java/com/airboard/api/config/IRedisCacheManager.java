package com.airboard.api.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @Description 自定义的shiro redis管理类
 */
@Slf4j
public class IRedisCacheManager implements CacheManager {

    private final ConcurrentMap<String, Cache> caches = new ConcurrentHashMap<String, Cache>();

    @Resource
    private RedisTemplate redisTemplate;

    @Override
    public <K, V> Cache<K, V> getCache(String name) throws CacheException {
        log.debug("获取名称为: " + name + " 的RedisCache实例");
        Cache cache = caches.get(name);
        if (cache == null) {
            cache = new IRedisCache<K, V>(name, redisTemplate);
            caches.put(name, cache);
        }
        return cache;
    }

}
