package com.airboard.api.service.system.impl;

import com.airboard.api.dao.system.SysPermissionRepository;
import com.airboard.api.model.system.SysPermission;
import com.airboard.api.dao.system.SysPermissionMapper;
import com.airboard.api.service.system.SysPermissionService;
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
public class SysPermissionServiceImpl extends ServiceImpl<SysPermissionMapper, SysPermission> implements SysPermissionService {
    @Autowired
    SysPermissionRepository sysPermissionRepository;
    @Autowired
    private SysPermissionMapper sysPermissionMapper;

    @Override
    public Integer countByPermission(String permission) {
        return sysPermissionRepository.countByPermission(permission);
    }

}
