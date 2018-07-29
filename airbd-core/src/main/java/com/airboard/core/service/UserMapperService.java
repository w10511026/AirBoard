package com.airboard.core.service;


import com.airboard.core.model.Users;
import com.airboard.core.base.BaseMapperService;

import java.util.List;

public interface UserMapperService extends BaseMapperService<Users, Long> {

    List<Users> getUserList();
}