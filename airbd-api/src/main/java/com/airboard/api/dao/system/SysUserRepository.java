package com.airboard.api.dao.system;

import com.airboard.api.model.system.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Description </br>
 * @Version Ver 1.0
 * @Author <a href="mailto:wangshuo@ebnew.com">Wangshuo</a>
 * @Date 2018/6/27
 */
public interface SysUserRepository extends JpaRepository<SysUser, Long> {

    List<SysUser> getByUserName(String userName);

    List<SysUser> getByUserNameAndPassWord(String userName, String passWord);
}
