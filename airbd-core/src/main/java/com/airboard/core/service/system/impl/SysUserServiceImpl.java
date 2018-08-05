package com.airboard.core.service.system.impl;

import com.airboard.core.base.BasePage;
import com.airboard.core.dao.system.SysUserMapper;
import com.airboard.core.model.system.SysUser;
import com.airboard.core.service.system.SysUserService;
import com.airboard.core.vo.SysUserVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Override
    public IPage<SysUser> listPageByCondition(BasePage basePage, SysUserVO sysUserVO) {
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(sysUserVO, sysUser);
        QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(sysUser.getUserName())) {
            wrapper.like("user_name", sysUser.getUserName());
        }
        IPage<SysUser> page = new Page<>(basePage.getPageIndex(), basePage.getPageSize());
        IPage iPage = selectPage(page, wrapper);
        return iPage;
    }
}