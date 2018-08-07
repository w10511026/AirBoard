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
@Table(name = "sys_role")//JPA标识
@TableName("sys_role")//mybatis标识
public class SysRole extends BaseObject<SysRole> {

    private static final long serialVersionUID = 1L;

    /**
     * 角色名称
     */
    private String name;


    @Override
    protected Serializable pkVal() {
       return this.id;
    }

}