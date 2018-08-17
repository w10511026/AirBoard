package com.airboard.api.vo.system;

import com.airboard.core.base.BaseObject;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("用户角色信息")
public class SysUserRoleVO extends BaseObject<SysUserRoleVO> {

    private static final long serialVersionUID = 1L;

    private Long userId;

    private Long roleId;

    @Override
    protected Serializable pkVal() {
       return this.id;
    }

}