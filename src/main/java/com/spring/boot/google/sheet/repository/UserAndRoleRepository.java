package com.spring.boot.google.sheet.repository;

import com.spring.boot.google.sheet.model.UserAndRole;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by FRAMGIA\dang.ngoc.thien on 16/11/2016.
 */
public interface UserAndRoleRepository extends CrudRepository<UserAndRole, Long> {

    @Query(value = "insert into user_role (user_id, role_id) values (?1, ?2)", nativeQuery = true)
    void settingService(int user_id, int role_id);
}
