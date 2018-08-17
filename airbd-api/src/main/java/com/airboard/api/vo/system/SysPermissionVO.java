package com.airboard.api.vo.system;

import com.airboard.core.base.BaseObject;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("权限信息")
public class SysPermissionVO extends BaseObject<SysPermissionVO> {

    private static final long serialVersionUID = 1L;

    private String name;

    private Integer type;

    private Integer level;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}