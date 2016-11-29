package com.spring.boot.google.sheet.model;

import javax.persistence.*;

/**
 * Created by FRAMGIA\dang.ngoc.thien on 16/11/2016.
 */
@Entity
@Table(name = "user_role")
public class UserAndRole {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, updatable = false)
    private Integer id;
    @Column(name = "user_id")
    private Integer user_Id;
    @Column(name = "role_id")
    private Integer role_Id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUser_Id() {
        return user_Id;
    }

    public void setUser_Id(Integer user_Id) {
        this.user_Id = user_Id;
    }

    public Integer getRole_Id() {
        return role_Id;
    }

    public void setRole_Id(Integer role_Id) {
        this.role_Id = role_Id;
    }
}
