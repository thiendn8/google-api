package com.spring.boot.google.sheet.service;

import com.spring.boot.google.sheet.model.User;
import com.spring.boot.google.sheet.model.UserAndRole;

/**
 * Created by FRAMGIA\dang.ngoc.thien on 16/11/2016.
 */
public interface IUserAndRole {
    void save(UserAndRole userAndRole);

    void updateRole(UserAndRole userAndRole);

    void deleteRole(UserAndRole userAndRole);
}
