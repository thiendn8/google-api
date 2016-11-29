package com.spring.boot.google.sheet.service.impl;

import com.spring.boot.google.sheet.model.UserAndRole;
import com.spring.boot.google.sheet.repository.UserAndRoleRepository;
import com.spring.boot.google.sheet.service.IUserAndRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by FRAMGIA\dang.ngoc.thien on 16/11/2016.
 */
@Service
public class UserAndRoleImpl implements IUserAndRole {
    @Autowired
    private UserAndRoleRepository userAndRoleRepository;

    @Override
    public void save(UserAndRole userAndRole) {
          userAndRoleRepository.save(userAndRole);
    }
}
