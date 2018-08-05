package com.airboard.core.model.system;

import java.io.Serializable;
import com.airboard.core.base.BaseObject;
import javax.persistence.*;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

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


    @Override
    protected Serializable pkVal() {
       return this.id;
    }

}