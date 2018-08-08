package com.airboard.core.vo;

import com.airboard.core.base.BaseObject;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@ApiModel("系统用户vo")
public class SysUserVO extends BaseObject {

    private String loginName;
    private String passWord;
    private String userName;
    private Integer userType;
    private Integer mobile;
    private Integer sex;
    private String email;
    private Integer status;
    private Integer cardNo;
    private String salt;
    private List<SysRoleVO> roles;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
