package com.airboard.core.base;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.io.Serializable;

@Data
@ToString
@MappedSuperclass
public abstract class BaseObject<T> implements Serializable {

    private static final long serialVersionUID = -1245891341026451451L;

    /**
     * 主键id
     */
    @Id
    @GeneratedValue(generator = "idGenerator")
    @GenericGenerator(name = "idGenerator", strategy = "com.airboard.core.base.IdGeneratorStrategy")
    protected Long id;
    /**
     * 版本
     */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "version")
    protected Integer version;
    /**
     * 创建时间
     */
    @Column(name = "create_time")
    protected Long createTime;
    /**
     * 创建人
     */
    @Column(name = "create_user_id")
    private Long createUserId;
    /**
     * 修改时间
     */
    @Column(name = "update_time")
    protected Long updateTime;
    /**
     * 修改人
     */
    @Column(name = "update_user_id")
    protected Long updateUserId;

    public BaseObject() {
        this.createTime = System.currentTimeMillis();
    }

    public BaseObject(Long id, Long createTime, Long createUserId) {
        super();
        this.id = id;
        this.createTime = createTime;
        this.createUserId = createUserId;
    }

    protected abstract Serializable pkVal();
}
