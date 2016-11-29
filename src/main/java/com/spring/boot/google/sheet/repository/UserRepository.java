package com.spring.boot.google.sheet.repository;

import com.spring.boot.google.sheet.model.Role;
import com.spring.boot.google.sheet.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by FRAMGIA\dang.ngoc.thien on 08/11/2016.
 */

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    @Query(value = "SELECT * FROM user", nativeQuery = true)
    List<User> getListUser();



}
