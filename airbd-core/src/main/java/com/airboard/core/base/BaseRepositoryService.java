package com.airboard.core.base;

import java.io.Serializable;
import java.util.List;

/**
 * @Description JPA基础service
 * @Author <a href="mailto:wangshuo@ebnew.com">Wangshuo</a>
 * @Date 2018/7/27
 */
public interface BaseRepositoryService<T extends BaseObject, ID extends Serializable> {

    List<T> listAll();

    T getById(ID id);

    void save(T t);

    void update(T t);

    void saveOrUpdate(T t);

    void deleteById(ID id);
}
