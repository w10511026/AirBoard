package com.airboard.airbd.elasticsearch.dao;

import com.airboard.airbd.elasticsearch.model.User;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Auther: Wansghuo
 * @Date: 2018/11/27 16:24
 */
@Component
public interface UserRepository extends ElasticsearchRepository<User, Long> {

    User getUserById(Long id);

}
