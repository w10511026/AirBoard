package com.airboard.core.base;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Data
@ToString
@MappedSuperclass
public class BaseObject<T> implements Serializable {

    private static final long serialVersionUID = -1245891341026451451L;

    /**
     * 主键id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;
    /**
     * 版本
     */
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
    }

    public BaseObject(Long id, Long createTime, Long createUserId) {
        super();
        this.id = id;
        this.createTime = createTime;
        this.createUserId = createUserId;
    }
}
