package com.spring.boot.google.sheet.dto;

/**
 * Created by FRAMGIA\dang.ngoc.thien on 09/11/2016.
 */
public class EntityTimes {
    public String timeIn;
    public String timeout;

    public EntityTimes() {
    }

    public EntityTimes(String timeIn, String timeout) {
        this.timeIn = timeIn;
        this.timeout = timeout;
    }

    public String getTimeIn() {
        return timeIn;
    }

    public void setTimeIn(String timeIn) {
        this.timeIn = timeIn;
    }

    public String getTimeout() {
        return timeout;
    }

    public void setTimeout(String timeout) {
        this.timeout = timeout;
    }
}
