package com.spring.boot.google.sheet.dto;

/**
 * Created by FRAMGIA\dang.ngoc.thien on 09/11/2016.
 */
public class StaffAndCode {
    /*Code of member*/
    private String employeeCode;
    /*Branch name of memeber*/
    private String staffName;

    public StaffAndCode() {
    }

    public String getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }
}
