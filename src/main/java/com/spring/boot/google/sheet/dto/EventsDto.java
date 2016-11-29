package com.spring.boot.google.sheet.dto;

import java.util.List;

/**
 * Created by FRAMGIA\dang.ngoc.thien on 22/11/2016.
 */
public class EventsDto {
    private String calendarId;
    private String summary;
    private String address;
    private String description;
    private String startTime;
    private String endTime;
    private String timeZone;
    private String mailInfrom;
    private String creator;
    private String eventId;

    public String getCalendarId() {
        return calendarId;
    }

    public void setCalendarId(String calendarId) {
        this.calendarId = calendarId;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public String getMailInfrom() {
        return mailInfrom;
    }

    public void setMailInfrom(String mailInfrom) {
        this.mailInfrom = mailInfrom;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    @Override
    public String toString() {
        return "EventsDto{" +
                "calendarId='" + calendarId + '\'' +
                ", summary='" + summary + '\'' +
                ", address='" + address + '\'' +
                ", description='" + description + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", timeZone='" + timeZone + '\'' +
                ", mailInfrom='" + mailInfrom + '\'' +
                ", creator='" + creator + '\'' +
                ", eventId='" + eventId + '\'' +
                '}';
    }
}
