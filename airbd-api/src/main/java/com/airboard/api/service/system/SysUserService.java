package com.airboard.api.service.system;

import com.airboard.core.base.BasePage;
import com.airboard.api.model.system.SysUser;
import com.airboard.api.vo.system.SysUserVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * 服务类
 *
 * @author Wangshuo123
 * @since 2018-08-01
 */
public interface SysUserService extends IService<SysUser> {

    /**
     * mybatis分页方法
     */
    IPage<SysUserVO> listIPageByCondition(BasePage basePage, SysUserVO sysUserVO);

    /**
     * JPA分页方法
     */
    Page<SysUser> listPageByCondition(BasePage basePage, SysUserVO sysUserVO);

    List<SysUserVO> getByUserName(String userName);

    void insertOrUpdate(SysUserVO sysUserVO);

}
