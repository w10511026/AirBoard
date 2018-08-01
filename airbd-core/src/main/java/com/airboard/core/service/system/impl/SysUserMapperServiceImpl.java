package com.airboard.core.service.system.impl;

import com.airboard.core.model.system.SysUser;
import com.airboard.core.dao.system.SysUserMapper;
import com.airboard.core.service.system.SysUserMapperService;
import com.airboard.core.base.AbstractBaseMapperService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *  服务实现类
 * @author Wangshuo123
 * @since 2018-08-01
 */
@Service
@Transactional
public class SysUserMapperServiceImpl extends AbstractBaseMapperService<SysUser, Long> implements SysUserMapperService {

}