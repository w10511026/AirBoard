package com.airboard.client.base;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public abstract class BaseObject<T> implements Serializable {

    private static final long serialVersionUID = 3712284858972175857L;

    protected Long id;

    protected Integer version;

    protected Long createTime;

    private Long createUserId;

    protected Long updateTime;

    protected Long updateUserId;

    protected Boolean _checked;

    public BaseObject() {
        this.createTime = System.currentTimeMillis();
    }

}
