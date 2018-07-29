package com.airboard.core.base;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.GeoRadiusResponse;
import redis.clients.jedis.JedisPool;

import java.util.List;
import java.util.Map;

/**
 * @Description REDIS通用方法类
 * @Author <a href="mailto:wangshuo@ebnew.com">Wangshuo</a>
 * @Date 2018/7/28
 */
@Service
public class JedisTemplate {

    @Autowired
    private JedisPool jedisPool;

    public boolean exists(String key) {
        boolean flag = false;
        flag = jedisPool.getResource().exists(key);
        return flag;
    }

    public String set(String key, String value, int seconds) {
        String responseResult = jedisPool.getResource().set(key, value);
        if (seconds != 0) {
            jedisPool.getResource().expire(key, seconds);
        }
        return responseResult;
    }

    public String getSet(String key, String value, int seconds) {
        String jedisClusterSet = jedisPool.getResource().getSet(key, value);
        jedisPool.getResource().expire(key, seconds);
        return jedisClusterSet;
    }

    public String get(String key) {
        String str = jedisPool.getResource().get(key);
        return str;
    }

    public Long geoadd(String key, double longitude, double latitude, byte[] obj) {
        return null;
    }

    public List<GeoRadiusResponse> georadius(String key, double longitude, double latitude) {
        return null;
    }

    public void delKey(String key) {
        jedisPool.getResource().del(key);
    }

    public void delNativeKey(String key) {
        jedisPool.getResource().del(key);
    }

    public Map<String, Object> getMapData(String key) {
        String str = jedisPool.getResource().get(key);
        Map<String, Object> map = JSON.parseObject(str, Map.class);
        return map;
    }

    /**
     * @Description: 如为第一次，则加上锁，每次调用值会自动加1
     */
    public boolean lock(String key, int seconds) {
        if (jedisPool.getResource().incr(key) == 1) {
            jedisPool.getResource().expire(key, seconds);
            return false;
        }
        return true;
    }

    public void unlock(String key) {
        jedisPool.getResource().del(key);
    }

    public String getLocakValue(String key) {
        return jedisPool.getResource().get(key);
    }
}
