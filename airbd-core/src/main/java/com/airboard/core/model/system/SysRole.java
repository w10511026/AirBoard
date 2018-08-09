package com.airboard.core.model.system;

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
@Table(name = "sys_role")//JPA标识
@TableName("sys_role")//mybatis标识
public class SysRole extends BaseObject<SysRole> {

    private static final long serialVersionUID = 1L;

    /**
     * 角色名称
     */
    private String name;

    //JPA注解，多对多关联查询
    @ManyToMany(cascade = {}, fetch = FetchType.EAGER)
    @JoinTable(name = "sys_role_permission",
            joinColumns = {@JoinColumn(name = "role_id")},
            inverseJoinColumns = {@JoinColumn(name = "permission_id")})
    //mbatis-plus注解，标识该字段非数据库字段
    @TableField(exist = false)
    private List<SysPermission> permissions;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}