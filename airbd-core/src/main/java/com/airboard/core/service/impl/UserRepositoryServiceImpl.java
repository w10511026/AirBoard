package com.airboard.core.service.impl;

import com.airboard.core.base.AbstractBaseRepositoryService;
import com.airboard.core.model.Users;
import com.airboard.core.service.UserRepositoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRepositoryServiceImpl extends AbstractBaseRepositoryService<Users, Long> implements UserRepositoryService {

    @Override
    public List<Users> getByUserName(String name) {
        return getByUserName(name);
    }
}