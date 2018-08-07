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
@Table(name = "sys_user_role")//JPA标识
@TableName("sys_user_role")//mybatis标识
public class SysUserRole extends BaseObject<SysUserRole> {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private Long userId;
    /**
     * 角色id
     */
    @Column(name = "role_id")
    private Long roleId;


    @Override
    protected Serializable pkVal() {
       return this.id;
    }

}