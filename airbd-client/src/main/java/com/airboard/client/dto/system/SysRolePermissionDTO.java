package com.airboard.client.dto.system;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("角色权限信息")
public class SysRolePermissionDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long roleId;

    private Long permissionId;

}