package com.spring.boot.google.sheet.dto;

import java.util.List;

/**
 * Created by FRAMGIA\dang.ngoc.thien on 08/11/2016.
 */
public class Employees {

    /*list employee*/
    public StaffAndCode staffAndCode;

    /*List schedule for check in and check out*/
    public List<ScheduleDateIO> listTimes;

    public Employees() {
    }

    public Employees(StaffAndCode staffAndCode, List<ScheduleDateIO> listTimes) {
        this.staffAndCode = staffAndCode;
        this.listTimes = listTimes;
    }

    public StaffAndCode getStaffAndCode() {
        return staffAndCode;
    }

    public void setStaffAndCode(StaffAndCode staffAndCode) {
        this.staffAndCode = staffAndCode;
    }

    public List<ScheduleDateIO> getListTimes() {
        return listTimes;
    }

    public void setListTimes(List<ScheduleDateIO> listTimes) {
        this.listTimes = listTimes;
    }
}
