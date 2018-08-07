package com.airboard.core.service.system.impl;

import com.airboard.core.model.system.SysUserRole;
import com.airboard.core.dao.system.SysUserRoleMapper;
import com.airboard.core.service.system.SysUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 *  服务实现类
 * @author Wangshuo
 * @since 2018-08-07
 */
@Service
@Transactional
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements SysUserRoleService {
    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

}