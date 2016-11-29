package com.spring.boot.google.sheet.repository;

import com.spring.boot.google.sheet.model.Person;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by FRAMGIA\dang.ngoc.thien on 08/11/2016.
 */

public interface RepositoryDao extends CrudRepository<Person, Long> {

    //get list person
    @Query(value = "SELECT * FROM PERSON", nativeQuery = true)
    List<Person> getListPerson();

}
