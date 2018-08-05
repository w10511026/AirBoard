package com.airboard.core.base;

import com.airboard.core.util.NumberUtils;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

/**
 * @Description JPA抽象通用service
 * @Author <a href="mailto:wangshuo@ebnew.com">Wangshuo</a>
 * @Date 2018/7/26
 */
@Slf4j
public abstract class AbstractBaseJPAService<T extends BaseObject, ID extends Serializable> implements BaseRepositoryService<T, ID> {

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
    public boolean insert(T t) {
        if (t == null) {
            log.info("待插入的实体为null，class:{}", this.getClz().getName());
            return false;
        }
        T result = jpaRepository.save(t);
        if (null == result) {
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteById(Serializable id) {
        if (id == null) {
            return false;
        }
        jpaRepository.deleteById((ID) id);
        String key = getClz().getName() + id;
        this.jedisTemplate.delKey(key);
        return true;
    }

    @Override
    public boolean updateById(T t) {
        if (t == null) {
            log.info("待插入的实体为null，class:{}", this.getClz().getName());
            return false;
        }
        if (NumberUtils.isEmpty(t.getId())) {
            log.info("待插入的实体的ID为null，class:{}", this.getClz().getName());
            return false;
        }
        T result = jpaRepository.save(t);
        if (null == result) {
            return false;
        }
        String key = getClz().getName() + t.getId();
        this.jedisTemplate.delKey(key);
        return true;
    }

    @Override
    public boolean insertOrUpdate(T t) {
        if (t == null) {
            log.info("待插入的实体为null，class:{}", this.getClz().getName());
            return false;
        }
        jpaRepository.save(t);
        //如果id不为空，则删除redis缓存数据
        if (NumberUtils.isEmpty(t.getId())) {
            return false;
        }
        String key = getClz().getName() + t.getId();
        this.jedisTemplate.delKey(key);
        return true;
    }

    @Override
    public T selectById(Serializable id) {
        String key = getClz().getName() + id;
        if (id == null) {
            return null;
        }
        String json = this.jedisTemplate.get(key);
        if (json != null) {
            return JSON.parseObject(json, getClz());
        }
        T t = jpaRepository.getOne((ID) id);
        if (t != null) {
            this.jedisTemplate.set(key, JSON.toJSONString(t), 0);
        }
        return t;
    }

    @Override
    public List<Map<String, Object>> selectMapList(T var1) {
        String key = getClz().getName() + REDIS_LIST_KEY;
        String json = jedisTemplate.get(key);
        if (StringUtils.isNotEmpty(json)) {
            return (List<Map<String, Object>>) JSON.parse(json);
        }
        List<T> list = jpaRepository.findAll();
        if (CollectionUtils.isNotEmpty(list)) {
            jedisTemplate.set(key, JSON.toJSONString(list), 60 * 3);
        }
        return (List<Map<String, Object>>) JSON.parse(list.toString());
    }

    @Override
    public int selectCount(T t) {
        Example example = Example.of(t);
        return Math.toIntExact(jpaRepository.count(example));
    }

    @Override
    public List<T> selectList(T var1) {
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
    public Page<T> selectPage(Pageable pageable, T t) {
        Example example = Example.of(t);
        return jpaRepository.findAll(example, pageable);
    }

}
