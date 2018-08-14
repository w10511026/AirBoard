package com.airboard.web.config;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @Description 自定义的shiro redis操作类
 */
@Slf4j
@Component
public class IRedisCache<K, V> implements Cache<K, V> {

    @Getter
    @Setter
    @Value("${shiro.cache.cache-prefix}")
    private String cachePrefix;

    @Getter
    @Setter
    @Value("${shiro.cache.cache-time}")
    private int cacheTime;

    private String cacheKey;

    private RedisTemplate<K, V> redisTemplate;

    public IRedisCache() {
        super();
    }

    public IRedisCache(String name, RedisTemplate<K, V> redisTemplate) {
        super();
        this.cacheKey = cachePrefix + name;
        this.redisTemplate = redisTemplate;
    }

    /**
     * 通过key来获取对应的缓存对象
     * 通过源码我们可以发现，shiro需要的key的类型为Object，V的类型为AuthorizationInfo对象
     */
    @Override
    public V get(K key) throws CacheException {
        log.info("根据key从Redis中获取对象 key [" + key + "]");
        try {
            if (key == null) {
                return null;
            } else {
                redisTemplate.boundValueOps(getCacheKey(key)).expire(cacheTime, TimeUnit.MINUTES);
                V v = redisTemplate.boundValueOps(getCacheKey(key)).get();
                return v;
            }
        } catch (Throwable t) {
            throw new CacheException(t);
        }
    }

    /**
     * 将权限信息加入缓存中
     */
    @Override
    public V put(K key, V value) throws CacheException {
        log.info("根据key从存储 key [" + key + "]");
        try {
            V v = get(key);
            redisTemplate.boundValueOps(getCacheKey(key)).set(value);
            System.out.println(redisTemplate.boundValueOps(getCacheKey(key)).get().toString());
            return v;
        } catch (Throwable t) {
            throw new CacheException(t);
        }
    }

    /**
     * 将权限信息从缓存中删除
     */
    @Override
    public V remove(K key) throws CacheException {
        log.info("从redis中删除 key [" + key + "]");
        try {
            V v = get(key);
            redisTemplate.delete(getCacheKey(key));
            return v;
        }catch (Throwable t){
            throw new CacheException(t);
        }
    }

    @Override
    public void clear() throws CacheException {
        try {
            redisTemplate.delete(keys());
        } catch (Throwable t) {
            throw new CacheException(t);
        }
    }

    @Override
    public int size() {
        return keys().size();
    }

    @Override
    public Set<K> keys() {
        return redisTemplate.keys(getCacheKey("*"));
    }

    @Override
    public Collection<V> values() {
        Set<K> set = keys();
        List<V> list = new ArrayList<>();
        for (K s : set) {
            list.add(get(s));
        }
        return list;
    }

    private K getCacheKey(Object k) {
        return (K) (this.cacheKey + k);
    }

}
