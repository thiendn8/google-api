package com.spring.boot.google.sheet.model;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by FRAMGIA\dang.ngoc.thien on 08/11/2016.
 */

@Entity
@Table(name = "role")
public class Role {
    private Integer id;
    private String name;
   private Set<User> users;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToMany(mappedBy = "roles")
    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
