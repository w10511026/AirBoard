package com.airboard.core.service;


import com.airboard.core.model.Users;
import com.airboard.core.base.BaseService;

import java.util.List;

public interface UserService extends BaseService<Users, Long> {

    List<Users> getUserList();
}