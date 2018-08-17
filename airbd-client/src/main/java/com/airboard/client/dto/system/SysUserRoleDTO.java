package com.airboard.client.dto.system;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("用户角色信息")
public class SysUserRoleDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long userId;

    private Long roleId;

}