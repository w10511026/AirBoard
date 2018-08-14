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
 * @Description:
 */
@Data
@Accessors(chain = true)
@Entity
@Table(name = "sys_permission")//JPA标识
@TableName("sys_permission")//mybatis标识
public class SysPermission extends BaseObject<SysPermission> {

    private static final long serialVersionUID = 1L;

    /**
     * 权限名称
     */
    private String name;
    /**
     * 权限标识
     */
    private String permission;
    /**
     * 权限路径
     */
    private String url;
    /**
     * 权限类型（1:菜单 2:按钮）
     */
    private Integer type;
    @Column(name = "parent_id")
    private String parentId;

    @ManyToMany
    @JoinTable(name = "sys_role_permission", joinColumns = {@JoinColumn(name = "permission_id")}, inverseJoinColumns = {@JoinColumn(name = "role_id")})
    @TableField(exist = false)
    private List<SysRole> roles;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
