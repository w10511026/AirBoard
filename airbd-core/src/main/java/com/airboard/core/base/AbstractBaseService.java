package com.airboard.core.base;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * @Description
 * @Author <a href="mailto:wangshuo@ebnew.com">Wangshuo</a>
 * @Date 2018/7/26
 */
@Slf4j
public abstract class AbstractBaseService<T extends BaseObject, ID extends Serializable> implements BaseService<T, ID> {

    @Autowired
    JedisTemplate jedisTemplate;

    @Setter
    @Getter
    @Autowired
    private BaseMapper<T, ID> baseMapper;

    private final static String REDIS_LIST_KEY = "_LIST";

    private Class<T> clz;

    Class<T> getClz() {
        if (clz == null) {
            clz = (Class<T>) (((ParameterizedType) (this.getClass().getGenericSuperclass())).getActualTypeArguments()[0]);
        }
        return clz;
    }

    @Override
    public List<T> findAll() {
        String key = getClz().getName() + REDIS_LIST_KEY;
        String json = jedisTemplate.get(key);
        if (StringUtils.isNotEmpty(json)) {
            return (List<T>) JSON.parse(json);
        }
        List<T> list = this.getBaseMapper().findAll();
        if (CollectionUtils.isNotEmpty(list)) {
            jedisTemplate.set(key, JSON.toJSONString(list), 60 * 3);
        }
        return list;
    }

    @Override
    public T getOne(ID id) {
        String key = getClz().getName() + id;
        if (id == null) {
            return null;
        }
        Gson gson = new Gson();
        String json = this.jedisTemplate.get(key);
        if (json != null) {
            return gson.fromJson(json, getClz());
        }
        T t = this.getBaseMapper().getOne(id);
        if (t != null) {
            this.jedisTemplate.set(key, gson.toJson(t), 0);
        }
        return t;
    }

    @Override
    public void insert(T t) {
        if (t == null) {
            log.info("待插入的实体为null，class:{}", this.getClz().getName());
            return;
        }
        this.getBaseMapper().insert(t);
    }

    @Override
    public void update(T t) {
        if (t == null) {
            log.info("待插入的实体为null，class:{}", this.getClz().getName());
            return;
        }
        this.getBaseMapper().update(t);
        String key = getClz().getName() + t.getId();
        this.jedisTemplate.delKey(key);
    }

    @Override
    public void delete(ID id) {
        if (id == null) {
            return;
        }
        this.getBaseMapper().delete(id);
        String key = getClz().getName() + id;
        this.jedisTemplate.delKey(key);
    }
}
