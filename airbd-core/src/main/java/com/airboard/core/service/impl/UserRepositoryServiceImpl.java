package com.airboard.core.service.impl;

import com.airboard.core.annotation.RedisCache;
import com.airboard.core.base.AbstractBaseRepositoryService;
import com.airboard.core.model.Users;
import com.airboard.core.service.UserRepositoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRepositoryServiceImpl extends AbstractBaseRepositoryService<Users, Long> implements UserRepositoryService {

    @RedisCache(type = Users.class)
    @Override
    public List<Users> getUserList() {
        return super.listAll();
    }
}