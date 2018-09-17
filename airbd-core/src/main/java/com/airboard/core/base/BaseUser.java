package com.airboard.core.base;

import com.airboard.client.dto.system.SysRoleDTO;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@ApiModel("当前登录用户")
public class BaseUser implements Serializable {

    private static final long serialVersionUID = -5668550068229702941L;

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
