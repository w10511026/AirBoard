package com.airboard.core.base;

import java.io.Serializable;
import java.util.List;

/**
 * @Description MYBATIS基础dao
 * @Author <a href="mailto:wangshuo@ebnew.com">Wangshuo</a>
 * @Date 2018/7/27
 */
public interface BaseMapper<T extends BaseObject, ID extends Serializable> {

    List<T> findAll();

    T getOne(ID id);

    void insert(T t);

    void update(T t);

    void delete(ID id);
}
