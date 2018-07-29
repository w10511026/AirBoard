package com.airboard.core.model;

import com.airboard.core.base.BaseObject;
import lombok.Data;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@ToString
@Entity
@Table(name = "users")
public class Users extends BaseObject implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(nullable = false, unique = true)
    private String userName;
    @Column(nullable = false)
    private String passWord;

    public Users() {
        super();
    }

    public Users(String userName, String passWord) {
        super();
        this.passWord = passWord;
        this.userName = userName;
    }

}