package com.airboard.client.dto.system;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@ApiModel("系统用户vo")
public class SysUserDTO implements Serializable {

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
    //秘钥
    private String token;

}
