package com.airboard.core.dao;

import com.airboard.core.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Description </br>
 * @Version Ver 1.0
 * @Author <a href="mailto:wangshuo@ebnew.com">Wangshuo</a>
 * @Date 2018/6/27
 */
public interface UserRepository extends JpaRepository<Users, Long> {
}
