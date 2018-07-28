package com.airboard.core.base;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Data
public abstract class BaseObject implements Serializable {

    private static final long serialVersionUID = -1245891341026451451L;

    public static final String ID = "id";

    public static final String CREATETIME = "createtime";

    public static final String CREATEUSERID = "createuserid";

    public static final String VERSION = "version";

    public static final String UPDATETIME = "updatetime";

    public static final String UPDATEUSERID = "updateuserid";

    /**
     * 主键id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;
    /**
     * 版本
     */
    protected Integer version;
    /**
     * 创建时间
     */
    protected Long createTime;
    /**
     * 创建人
     */
    private Long createUserId;
    /**
     * 修改时间
     */
    protected Long updateTime;
    /**
     * 修改人
     */
    protected Long updateUserId;

    public BaseObject() {
    }

    public BaseObject(Long id, Long createTime, Long createUserId) {
        super();
        this.id = id;
        this.createTime = createTime;
        this.createUserId = createUserId;
    }
}
