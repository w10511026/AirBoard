package com.airboard.core.base;

import com.airboard.core.util.NumberUtils;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * @Description JPA抽象通用service
 * @Author <a href="mailto:wangshuo@ebnew.com">Wangshuo</a>
 * @Date 2018/7/26
 */
@Slf4j
public abstract class AbstractBaseRepositoryService<T extends BaseObject, ID extends Serializable> implements BaseRepositoryService<T, ID> {

    @Autowired
    JedisTemplate jedisTemplate;

    @Autowired
    private JpaRepository<T, ID> jpaRepository;

    private final static String REDIS_LIST_KEY = "_LIST";

    private Class<T> clz;

    Class<T> getClz() {
        if (clz == null) {
            clz = (Class<T>) (((ParameterizedType) (this.getClass().getGenericSuperclass())).getActualTypeArguments()[0]);
        }
        return clz;
    }

    @Override
    public List<T> listAll() {
        String key = getClz().getName() + REDIS_LIST_KEY;
        String json = jedisTemplate.get(key);
        if (StringUtils.isNotEmpty(json)) {
            return (List<T>) JSON.parse(json);
        }
        List<T> list = jpaRepository.findAll();
        if (CollectionUtils.isNotEmpty(list)) {
            jedisTemplate.set(key, JSON.toJSONString(list), 60 * 3);
        }
        return list;
    }

    @Override
    public T getById(ID id) {
        String key = getClz().getName() + id;
        if (id == null) {
            return null;
        }
        String json = this.jedisTemplate.get(key);
        if (json != null) {
            return JSON.parseObject(json, getClz());
        }
        T t = jpaRepository.getOne(id);
        if (t != null) {
            this.jedisTemplate.set(key, JSON.toJSONString(t), 0);
        }
        return t;
    }

    @Override
    public void save(T t) {
        if (t == null) {
            log.info("待插入的实体为null，class:{}", this.getClz().getName());
            return;
        }
        jpaRepository.save(t);
    }

    @Override
    public void update(T t) {
        if (t == null) {
            log.info("待插入的实体为null，class:{}", this.getClz().getName());
            return;
        }
        if (NumberUtils.isEmpty(t.getId())) {
            log.info("待插入的实体的ID为null，class:{}", this.getClz().getName());
            return;
        }
        jpaRepository.save(t);
        String key = getClz().getName() + t.getId();
        this.jedisTemplate.delKey(key);
    }

    @Override
    public void saveOrUpdate(T t) {
        if (t == null) {
            log.info("待插入的实体为null，class:{}", this.getClz().getName());
            return;
        }
        jpaRepository.save(t);
        //如果id不为空，则删除redis缓存数据
        if (NumberUtils.isNotEmpty(t.getId())) {
            String key = getClz().getName() + t.getId();
            this.jedisTemplate.delKey(key);
        }
    }

    @Override
    public void deleteById(ID id) {
        if (id == null) {
            return;
        }
        jpaRepository.deleteById(id);
        String key = getClz().getName() + id;
        this.jedisTemplate.delKey(key);
    }
}
