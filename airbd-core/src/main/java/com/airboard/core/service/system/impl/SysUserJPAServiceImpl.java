package com.airboard.core.service.system.impl;

import com.airboard.core.base.AbstractBaseJPAService;
import com.airboard.core.dao.system.SysUserRepository;
import com.airboard.core.model.system.SysUser;
import com.airboard.core.service.system.SysUserJPAService;
import com.airboard.core.vo.SysUserVO;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysUserJPAServiceImpl extends AbstractBaseJPAService<SysUser, Long> implements SysUserJPAService {

    @Autowired
    private SysUserRepository sysUserRepository;

    @Override
    public List<SysUserVO> getByUserName(String userName) {
        List<SysUser> userList = sysUserRepository.getByUserName(userName);
        List<SysUserVO> resultList = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(userList)) {
            userList.forEach(item -> {
                SysUserVO sysUserVO = new SysUserVO();
                BeanUtils.copyProperties(item, sysUserVO);
                resultList.add(sysUserVO);
            });
        }
        return resultList;
    }

    @Override
    public List<SysUser> getByUserNameAndPassWord(String userName, String passWord) {
        return sysUserRepository.getByUserNameAndPassWord(userName, passWord);
    }
}
