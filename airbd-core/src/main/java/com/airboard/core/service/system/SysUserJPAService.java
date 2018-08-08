package com.airboard.core.service.system;


import com.airboard.core.base.BaseRepositoryService;
import com.airboard.core.model.system.SysUser;
import com.airboard.core.vo.SysUserVO;

import java.util.List;

public interface SysUserJPAService extends BaseRepositoryService<SysUser, Long> {

    List<SysUserVO> getByUserName(String userName);

    List<SysUser> getByUserNameAndPassWord(String userName, String passWord);

}
