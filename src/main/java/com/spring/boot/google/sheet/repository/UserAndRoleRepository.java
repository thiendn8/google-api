package com.spring.boot.google.sheet.repository;

import com.spring.boot.google.sheet.model.UserAndRole;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by FRAMGIA\dang.ngoc.thien on 16/11/2016.
 */
public interface UserAndRoleRepository extends CrudRepository<UserAndRole, Long> {

    @Query(value = "insert into user_role (user_id, role_id) values (?1, ?2)", nativeQuery = true)
    void settingService(int user_id, int role_id);

    @Modifying
    @Query(value = "update user_role set role_id = ?1 where user_id = ?2", nativeQuery = true)
    @Transactional
    void updateRoleUser(int role_id, int user_id);

    @Modifying
    @Query(value = "delete from user_role where user_id = ?1 and role_id = ?2", nativeQuery = true)
    @Transactional
    void deleteRoleUser(int user_id, int role_id);
}
