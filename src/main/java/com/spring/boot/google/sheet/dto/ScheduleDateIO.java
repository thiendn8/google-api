package com.spring.boot.google.sheet.dto;

/**
 * Created by FRAMGIA\dang.ngoc.thien on 09/11/2016.
 */
public class ScheduleDateIO {

    public String dateTime;
    public EntityTimes entityTimes;

    public ScheduleDateIO() {
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public EntityTimes getEntityTimes() {
        return entityTimes;
    }

    public void setEntityTimes(EntityTimes entityTimes) {
        this.entityTimes = entityTimes;
    }
}
