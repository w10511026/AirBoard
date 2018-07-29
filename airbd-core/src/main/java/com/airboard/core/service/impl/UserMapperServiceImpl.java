package com.airboard.core.service.impl;

import com.airboard.core.annotation.RedisCache;
import com.airboard.core.model.Users;
import com.airboard.core.service.UserMapperService;
import com.airboard.core.base.AbstractBaseMapperService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserMapperServiceImpl extends AbstractBaseMapperService<Users, Long> implements UserMapperService {

    @RedisCache(type = Users.class)
    @Override
    public List<Users> getUserList() {
        return super.listAll();
    }
}