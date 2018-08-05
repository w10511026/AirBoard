package com.airboard.core.service.system.impl;

import com.airboard.core.dao.system.SysUserMapper;
import com.airboard.core.model.system.SysUser;
import com.airboard.core.service.system.SysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

}