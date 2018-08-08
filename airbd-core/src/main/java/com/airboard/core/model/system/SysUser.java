package com.airboard.core.model.system;

import java.io.Serializable;
import java.util.List;

import com.airboard.core.base.BaseObject;
import javax.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @Description:
 * @author Wangshuo
 * @since 2018-08-07
 */
@Data
@Accessors(chain = true)
@Entity
@Table(name = "sys_user")//JPA标识
@TableName("sys_user")//mybatis标识
public class SysUser extends BaseObject<SysUser> {

    private static final long serialVersionUID = 1L;

    @Column(name = "login_name")
    private String loginName;
    @Column(name = "pass_word")
    private String passWord;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "user_type")
    private Integer userType;
    private Integer mobile;
    private Integer sex;
    private String email;
    private Integer status;
    @Column(name = "card_no")
    private Integer cardNo;
    private String salt;
    @ManyToMany(cascade = {}, fetch = FetchType.EAGER)
    @JoinTable(name = "sys_user_role",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private List<SysRole> roles;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}