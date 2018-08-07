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
@Table(name = "sys_permission")//JPA标识
@TableName("sys_permission")//mybatis标识
public class SysPermission extends BaseObject<SysPermission> {

    private static final long serialVersionUID = 1L;

    /**
     * 权限名称
     */
    private String name;
    /**
     * 权限类型（1:菜单 2:按钮）
     */
    private Integer type;
    /**
     * 菜单级别(1:一级菜单 2:二级菜单 3:三级菜单)
     */
    private Integer level;


    @Override
    protected Serializable pkVal() {
       return this.id;
    }

}