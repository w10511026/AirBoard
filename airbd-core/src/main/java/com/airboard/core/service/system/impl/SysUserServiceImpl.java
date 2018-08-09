package com.airboard.core.service.system.impl;

import com.airboard.core.annotation.RedisCache;
import com.airboard.core.base.BasePage;
import com.airboard.core.dao.system.SysUserMapper;
import com.airboard.core.dao.system.SysUserRepository;
import com.airboard.core.model.system.SysUser;
import com.airboard.core.service.system.SysUserService;
import com.airboard.core.vo.SysPermissionVO;
import com.airboard.core.vo.SysRoleVO;
import com.airboard.core.vo.SysUserVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private SysUserRepository sysUserRepository;

    @Override
    public IPage<SysUser> listIPageByCondition(BasePage basePage, SysUserVO sysUserVO) {
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(sysUserVO, sysUser);
        QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(sysUser.getUserName())) {
            wrapper.like("user_name", sysUser.getUserName());
        }
        IPage<SysUser> page = new Page<>(basePage.getPageIndex(), basePage.getPageSize());
        return sysUserMapper.selectPage(page, wrapper);
    }

    @Override
    public org.springframework.data.domain.Page<SysUser> listPageByCondition(BasePage basePage, SysUserVO sysUserVO) {
        Pageable pageable = PageRequest.of(basePage.getPageIndex(), basePage.getPageSize(), new Sort(Sort.Direction.ASC, "id"));
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(sysUserVO, sysUser);
        return sysUserRepository.findAll(Example.of(sysUser), pageable);
    }

    @RedisCache(type = SysUserVO.class)
    @Override
    public List<SysUserVO> getByUserName(String userName) {
        List<SysUser> userList = sysUserRepository.getByUserName(userName);
        List<SysUserVO> resultList = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(userList)) {
            userList.forEach(item -> {
                SysUserVO sysUserVO = new SysUserVO();
                BeanUtils.copyProperties(item, sysUserVO);
                if (CollectionUtils.isNotEmpty(item.getRoles())) {
                    List<SysRoleVO> roleVOList = Lists.newArrayList();
                    item.getRoles().forEach(sysRole -> {
                        SysRoleVO sysRoleVO = new SysRoleVO();
                        BeanUtils.copyProperties(sysRole, sysRoleVO);
                        if (CollectionUtils.isNotEmpty(sysRole.getPermissions())) {
                            List<SysPermissionVO> permissionVOList = Lists.newArrayList();
                            sysRole.getPermissions().forEach(sysPermission -> {
                                SysPermissionVO permissionVO = new SysPermissionVO();
                                BeanUtils.copyProperties(sysPermission, permissionVO);
                                permissionVOList.add(permissionVO);
                            });
                            sysRoleVO.setPermissions(permissionVOList);
                        }
                        roleVOList.add(sysRoleVO);
                    });
                    sysUserVO.setRoles(roleVOList);
                }
                resultList.add(sysUserVO);
            });
        }
        return resultList;
    }

    @Override
    public void insertOrUpdate(SysUserVO sysUserVO) {
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(sysUserVO, sysUser);
        this.sysUserRepository.save(sysUser);
    }
}
