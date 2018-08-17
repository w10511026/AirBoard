package com.airboard.api.service.system.impl;

import com.airboard.client.enums.SysUserSexEnum;
import com.airboard.client.enums.SysUserStatusEnum;
import com.airboard.client.enums.SysUserTypeEnum;
import com.airboard.core.annotation.RedisCache;
import com.airboard.core.base.BasePage;
import com.airboard.api.dao.system.SysUserMapper;
import com.airboard.api.dao.system.SysUserRepository;
import com.airboard.api.model.system.SysUser;
import com.airboard.api.service.system.SysUserService;
import com.airboard.client.dto.system.SysPermissionDTO;
import com.airboard.client.dto.system.SysRoleDTO;
import com.airboard.client.dto.system.SysUserDTO;
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
    public IPage<SysUserDTO> listIPageByCondition(BasePage basePage, SysUserDTO sysUserVO) {
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(sysUserVO, sysUser);
        QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(sysUser.getUserName())) {
            wrapper.lambda().like(SysUser::getUserName, sysUser.getUserName());
        }
        IPage<SysUserDTO> result = new Page<>();
        IPage<SysUser> page = sysUserMapper.selectPage(new Page<SysUser>(basePage.getPageIndex(), basePage.getPageSize()), wrapper);
        if (null != page && CollectionUtils.isNotEmpty(page.getRecords())) {
            List<SysUserDTO> sysUserVOList = Lists.newArrayList();
            for (SysUser record : page.getRecords()) {
                SysUserDTO userVO = new SysUserDTO();
                BeanUtils.copyProperties(record, userVO);
                userVO.setUserTypeZh(SysUserTypeEnum.getEnumByType(record.getUserType()).name);
                userVO.setSexZh(SysUserSexEnum.getEnumByType(record.getSex()).name);
                userVO.setStatusZh(SysUserStatusEnum.getEnumByType(record.getStatus()).name);
                sysUserVOList.add(userVO);
            }
            result.setTotal(page.getTotal());
            result.setRecords(sysUserVOList);
        }
        return result;
    }

    @Override
    public org.springframework.data.domain.Page<SysUser> listPageByCondition(BasePage basePage, SysUserDTO sysUserVO) {
        Pageable pageable = PageRequest.of(basePage.getPageIndex(), basePage.getPageSize(), new Sort(Sort.Direction.ASC, "id"));
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(sysUserVO, sysUser);
        return sysUserRepository.findAll(Example.of(sysUser), pageable);
    }

    @RedisCache(type = SysUserDTO.class)
    @Override
    public List<SysUserDTO> getByUserName(String userName) {
        List<SysUser> userList = sysUserRepository.getByUserName(userName);
        List<SysUserDTO> resultList = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(userList)) {
            userList.forEach(item -> {
                SysUserDTO sysUserVO = new SysUserDTO();
                BeanUtils.copyProperties(item, sysUserVO);
                if (CollectionUtils.isNotEmpty(item.getRoles())) {
                    List<SysRoleDTO> roleVOList = Lists.newArrayList();
                    item.getRoles().forEach(sysRole -> {
                        SysRoleDTO sysRoleDTO = new SysRoleDTO();
                        BeanUtils.copyProperties(sysRole, sysRoleDTO);
                        if (CollectionUtils.isNotEmpty(sysRole.getPermissions())) {
                            List<SysPermissionDTO> permissionVOList = Lists.newArrayList();
                            sysRole.getPermissions().forEach(sysPermission -> {
                                SysPermissionDTO permissionVO = new SysPermissionDTO();
                                BeanUtils.copyProperties(sysPermission, permissionVO);
                                permissionVOList.add(permissionVO);
                            });
                            sysRoleDTO.setPermissions(permissionVOList);
                        }
                        roleVOList.add(sysRoleDTO);
                    });
                    sysUserVO.setRoles(roleVOList);
                }
                resultList.add(sysUserVO);
            });
        }
        return resultList;
    }

    @Override
    public void insertOrUpdate(SysUserDTO sysUserVO) {
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(sysUserVO, sysUser);
        this.sysUserRepository.save(sysUser);
    }
}
