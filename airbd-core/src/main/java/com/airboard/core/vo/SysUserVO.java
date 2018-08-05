package com.airboard.core.vo;

import com.airboard.core.base.BaseObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("系统用户vo")
public class SysUserVO extends BaseObject {

    @ApiModelProperty("学生姓名")
    private String userName;

    @ApiModelProperty("学生姓名")
    private String passWord;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
