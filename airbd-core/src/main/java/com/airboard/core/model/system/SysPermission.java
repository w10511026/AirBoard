package com.airboard.core.model.system;

import java.io.Serializable;
import com.airboard.core.base.BaseObject;
import javax.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;
import com.baomidou.mybatisplus.annotation.TableName;

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
    private String permission;
    @Column(name = "access_url")
    private String accessUrl;
    /**
     * 权限类型（1:菜单 2:按钮）
     */
    private Integer type;
    private String parent;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
