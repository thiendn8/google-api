package com.spring.boot.google.sheet.repository;

import com.spring.boot.google.sheet.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by FRAMGIA\dang.ngoc.thien on 08/11/2016.
 */

public interface RoleRepository extends JpaRepository<Role, Long>{

    @Query(value = "select * from role as role_ where role_.id in (?1)", nativeQuery = true)
    List<Role> getListRoleById(Integer id);

    @Query(value = "SELECT * FROM role", nativeQuery = true)
    List<Role> getListRole();
}
