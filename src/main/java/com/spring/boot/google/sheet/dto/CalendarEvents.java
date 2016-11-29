package com.spring.boot.google.sheet.dto;

import java.util.List;

/**
 * Created by FRAMGIA\dang.ngoc.thien on 21/11/2016.
 */
public class CalendarEvents {
    private String nameCreater;
    private String currentDate;
    private List<EventCalendar> listEvent;

    public String getNameCreater() {
        return nameCreater;
    }

    public void setNameCreater(String nameCreater) {
        this.nameCreater = nameCreater;
    }

    public String getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(String currentDate) {
        this.currentDate = currentDate;
    }

    public List<EventCalendar> getListEvent() {
        return listEvent;
    }

    public void setListEvent(List<EventCalendar> listEvent) {
        this.listEvent = listEvent;
    }

    @Override
    public String toString() {
        return "CalendarEvents{" +
                "nameCreater='" + nameCreater + '\'' +
                ", currentDate='" + currentDate + '\'' +
                ", listEvent=" + listEvent +
                '}';
    }
}
