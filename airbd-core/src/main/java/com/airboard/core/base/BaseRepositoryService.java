package com.airboard.core.base;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @Description JPA基础service
 * @Author <a href="mailto:wangshuo@ebnew.com">Wangshuo</a>
 * @Date 2018/7/27
 */
public interface BaseRepositoryService<T extends BaseObject, ID extends Serializable> {

    boolean insert(T var1);

    boolean deleteById(Serializable var1);

    boolean updateById(T var1);

    boolean insertOrUpdate(T var1);

    T selectById(Serializable var1);

    List<Map<String, Object>> selectMapList(T var1);

    int selectCount(T var1);

    List<T> selectList(T var1);

    Page<T> selectPage(Pageable pageable, T var1);
}
