package com.airboard.core.vo;

import com.airboard.core.base.BaseObject;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("角色权限信息")
public class SysRolePermissionVO extends BaseObject<SysRolePermissionVO> {

    private static final long serialVersionUID = 1L;

    private Long roleId;

    private Long permissionId;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}