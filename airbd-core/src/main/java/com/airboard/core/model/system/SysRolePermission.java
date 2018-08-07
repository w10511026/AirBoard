package com.airboard.core.model.system;

import java.io.Serializable;
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
@Table(name = "sys_role_permission")//JPA标识
@TableName("sys_role_permission")//mybatis标识
public class SysRolePermission extends BaseObject<SysRolePermission> {

    private static final long serialVersionUID = 1L;

    /**
     * 角色id
     */
    @Column(name = "role_id")
    private Long roleId;
    /**
     * 权限id
     */
    @Column(name = "permission_id")
    private Long permissionId;


    @Override
    protected Serializable pkVal() {
       return this.id;
    }

}