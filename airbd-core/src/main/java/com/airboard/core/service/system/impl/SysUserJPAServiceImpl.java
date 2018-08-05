package com.airboard.core.service.system.impl;

import com.airboard.core.base.AbstractBaseJPAService;
import com.airboard.core.dao.system.SysUserRepository;
import com.airboard.core.model.system.SysUser;
import com.airboard.core.service.system.SysUserJPAService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysUserJPAServiceImpl extends AbstractBaseJPAService<SysUser, Long> implements SysUserJPAService {

    @Autowired
    private SysUserRepository sysUserRepository;

    @Override
    public List<SysUser> getByUserName(String userName) {
        return sysUserRepository.getByUserName(userName);
    }

    @Override
    public List<SysUser> getByUserNameAndPassWord(String userName, String passWord) {
        return sysUserRepository.getByUserNameAndPassWord(userName, passWord);
    }
}