package com.spring.boot.google.sheet.dto;

/**
 * Created by FRAMGIA\dang.ngoc.thien on 09/11/2016.
 */
public class DateByMonth{
    public String dateTime;

    public DateByMonth(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
}
