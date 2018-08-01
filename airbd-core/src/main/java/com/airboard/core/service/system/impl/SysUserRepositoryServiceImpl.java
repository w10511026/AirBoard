package com.airboard.core.service.system.impl;

import com.airboard.core.base.AbstractBaseRepositoryService;
import com.airboard.core.dao.UserRepository;
import com.airboard.core.model.system.SysUser;
import com.airboard.core.service.system.SysUserRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysUserRepositoryServiceImpl extends AbstractBaseRepositoryService<SysUser, Long> implements SysUserRepositoryService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<SysUser> getByUserName(String userName) {
        return userRepository.getByUserName(userName);
    }

    @Override
    public List<SysUser> getByUserNameAndPassWord(String userName, String passWord) {
        return userRepository.getByUserNameAndPassWord(userName, passWord);
    }
}