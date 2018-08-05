package com.airboard.core.base;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Data
@ToString
@MappedSuperclass
public abstract class BaseObject<T> implements Serializable {

    private static final long serialVersionUID = -1245891341026451451L;

    //mybatis标识
    @TableId(type = IdType.ID_WORKER)
    //JPA标识
    @Id
    @GeneratedValue(generator = "idGenerator")
    @GenericGenerator(name = "idGenerator", strategy = "com.airboard.core.base.IdGeneratorStrategy")
    protected Long id;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "version")
    protected Integer version;

    @Column(name = "create_time")
    protected Long createTime;

    @Column(name = "create_user_id")
    private Long createUserId;

    @Column(name = "update_time")
    protected Long updateTime;

    @Column(name = "update_user_id")
    protected Long updateUserId;

    public BaseObject() {
        this.createTime = System.currentTimeMillis();
    }

    protected abstract Serializable pkVal();
}
