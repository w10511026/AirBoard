package com.airboard.api.model.system;

import com.airboard.core.base.BaseObject;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author Wangshuo
 * @Description:
 * @since 2018-08-07
 */
@Data
@Accessors(chain = true)
@Entity
@Table(name = "sys_user")//JPA标识
@TableName("sys_user")//mybatis标识
public class SysUser extends BaseObject<SysUser> {

    private static final long serialVersionUID = 1L;

    @Column(name = "user_name")
    private String userName;
    @Column(name = "pass_word")
    private String passWord;
    @Column(name = "nick_name")
    private String nickName;
    @Column(name = "user_type")
    private Integer userType;
    private Long mobile;
    private Integer sex;
    private String email;
    private Integer status;
    @Column(name = "card_no")
    private Integer cardNo;
    private String salt;

    //JPA注解，多对多关联查询
    @ManyToMany(cascade = {}, fetch = FetchType.EAGER)
    @JoinTable(name = "sys_user_role",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")})
    //mbatis-plus注解，标识该字段非数据库字段
    @TableField(exist = false)
    private List<SysRole> roles;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
