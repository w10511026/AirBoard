package com.airboard.core.model.vo;

import com.airboard.core.base.BaseObject;
import lombok.Data;

import java.io.Serializable;

@Data
public class UserVO extends BaseObject {

    private String userName;

    private String passWord;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
