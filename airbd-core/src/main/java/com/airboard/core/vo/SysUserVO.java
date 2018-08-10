package com.airboard.core.vo;

import com.airboard.core.base.BaseObject;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@ApiModel("系统用户vo")
public class SysUserVO extends BaseObject {

    private String userName;
    private String passWord;
    private String nickName;
    private Integer userType;
    private String userTypeZh;
    private Integer mobile;
    private Integer sex;
    private String sexZh;
    private String email;
    private Integer status;
    private String statusZh;
    private Integer cardNo;
    private String salt;
    private List<SysRoleVO> roles;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
