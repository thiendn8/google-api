package com.spring.boot.google.sheet.service;

import com.spring.boot.google.sheet.dto.Employees;
import com.spring.boot.google.sheet.model.Person;

import java.util.List;

/**
 * Created by FRAMGIA\dang.ngoc.thien on 08/11/2016.
 */
public interface IDataTimeSheetService {

    public List<Person> getListPerson();

    public Employees getEmployeeDetail(String code);

    public List<String> getListMonth();

}
