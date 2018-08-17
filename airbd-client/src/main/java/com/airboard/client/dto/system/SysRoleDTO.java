package com.airboard.client.dto.system;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@ApiModel("用户角色信息")
public class SysRoleDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;

    private List<SysPermissionDTO> permissions;

}