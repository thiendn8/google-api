package com.spring.boot.google.sheet.dto;

/**
 * Created by FRAMGIA\dang.ngoc.thien on 21/11/2016.
 */
public class EventCalendar {
    private String eventId;
    private String roomName;
    private String description;
    private String timeFrom;
    private String timeTo;
    private String creator;

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTimeFrom() {
        return timeFrom;
    }

    public void setTimeFrom(String timeFrom) {
        this.timeFrom = timeFrom;
    }

    public String getTimeTo() {
        return timeTo;
    }

    public void setTimeTo(String timeTo) {
        this.timeTo = timeTo;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    @Override
    public String toString() {
        return "EventCalendar{" +
                "eventId='" + eventId + '\'' +
                ", roomName='" + roomName + '\'' +
                ", description='" + description + '\'' +
                ", timeFrom='" + timeFrom + '\'' +
                ", timeTo='" + timeTo + '\'' +
                ", creator='" + creator + '\'' +
                '}';
    }
}
