package com.spring.boot.google.sheet.service;

import com.spring.boot.google.sheet.model.Role;
import com.spring.boot.google.sheet.model.User;
import com.spring.boot.google.sheet.model.UserAndRole;

import java.util.List;


/**
 * Created by FRAMGIA\dang.ngoc.thien on 08/11/2016.
 */

public interface UserService {
    void save(User user);

    User findByUsername(String username);

    List<User> getListUser();

    List<Role> getListRole();
}
