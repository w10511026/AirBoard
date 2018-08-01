package com.airboard.core.bo;

import com.airboard.core.base.BaseObject;
import lombok.Data;

import java.io.Serializable;

@Data
public class SysUserBO extends BaseObject {

    private String userName;

    private String passWord;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
