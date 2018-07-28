package com.airboard.core.service.impl;

import com.airboard.core.annotation.RedisCache;
import com.airboard.core.dto.Users;
import com.airboard.core.service.UserService;
import com.airboard.core.base.AbstractBaseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl extends AbstractBaseService<Users, Long> implements UserService {

    @RedisCache(type = Users.class)
    @Override
    public List<Users> getUserList() {
        return super.findAll();
    }
}