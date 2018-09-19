package com.airboard.client.dto.system;

import com.airboard.client.base.BaseObject;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@ApiModel("系统用户vo")
public class SysUserDTO extends BaseObject {

    private static final long serialVersionUID = 8556777790990421543L;

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
    private List<SysRoleDTO> roles;
    private String token;

}
