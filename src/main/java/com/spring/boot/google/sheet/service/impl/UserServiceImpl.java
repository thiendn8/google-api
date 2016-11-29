package com.spring.boot.google.sheet.service.impl;

import com.spring.boot.google.sheet.model.Role;
import com.spring.boot.google.sheet.model.User;
import com.spring.boot.google.sheet.repository.RoleRepository;
import com.spring.boot.google.sheet.repository.UserAndRoleRepository;
import com.spring.boot.google.sheet.repository.UserRepository;
import com.spring.boot.google.sheet.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

/**
 * Created by FRAMGIA\dang.ngoc.thien on 08/11/2016.
 */

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public List<User> getListUser() {
        return userRepository.getListUser();
    }

    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(new HashSet<>(roleRepository.getListRoleById(1)));
        userRepository.save(user);
    }

    @Override
    public List<Role> getListRole() {
        return roleRepository.getListRole();
    }


    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
