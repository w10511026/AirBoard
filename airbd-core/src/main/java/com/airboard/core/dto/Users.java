package com.airboard.core.dto;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "users")
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    @Override
    public String toString() {
        return "userName " + this.userName + ", pasword " + this.passWord;
    }

}