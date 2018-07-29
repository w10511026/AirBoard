package com.airboard.core.service;


import com.airboard.core.base.BaseRepositoryService;
import com.airboard.core.model.Users;

import java.util.List;

public interface UserRepositoryService extends BaseRepositoryService<Users, Long> {

    List<Users> getUserList();
}