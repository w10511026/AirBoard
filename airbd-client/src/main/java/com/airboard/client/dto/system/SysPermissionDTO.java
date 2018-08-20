package com.airboard.client.dto.system;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("权限信息")
public class SysPermissionDTO implements Serializable {

    private static final long serialVersionUID = 8342434900746627838L;

    private String name;

    private Integer type;

    private Integer level;

}