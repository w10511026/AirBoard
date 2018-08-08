package com.airboard.core.vo;

import com.airboard.core.base.BaseObject;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@ApiModel("用户角色信息")
public class SysRoleVO extends BaseObject<SysRoleVO> {

    private static final long serialVersionUID = 1L;

    private String name;

    private List<SysPermissionVO> permissions;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}