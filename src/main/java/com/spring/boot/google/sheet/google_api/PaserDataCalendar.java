package com.spring.boot.google.sheet.google_api;

import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.Events;
import com.spring.boot.google.sheet.commons.CommonUtils;
import com.spring.boot.google.sheet.commons.Constants;
import com.spring.boot.google.sheet.commons.StringUtils;
import com.spring.boot.google.sheet.dto.CalendarEvents;
import com.spring.boot.google.sheet.dto.EventCalendar;
import com.spring.boot.google.sheet.dto.EventsDto;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.*;

/**
 * Created by FRAMGIA\dang.ngoc.thien on 21/11/2016.
 */
public class PaserDataCalendar {

    private static final Logger logger = LogManager.getLogger(PaserDataCalendar.class);

   public static CalendarEvents getCalendars(){

       try {
           Events events = Quickstart_Calendar.getListEvents();
           String nameCreater = events.getSummary();
           List<Event> listEvent = events.getItems();
           CalendarEvents calendarEvents = new CalendarEvents();
           calendarEvents.setNameCreater(nameCreater);
           List<EventCalendar> listEvents= new ArrayList<>();
           EventCalendar eventCalendar = null;
           if(listEvent != null){
               for (Event event: listEvent){
                   eventCalendar = new EventCalendar();
                   calendarEvents.setCurrentDate(getCurrentDate(event.getEnd().getDateTime()));
                   eventCalendar.setEventId(event.getId());
                   eventCalendar.setTimeFrom(getTimeFrom(event.getStart().getDateTime()));
                   eventCalendar.setTimeTo(getTimeTo(event.getEnd().getDateTime()));
                   eventCalendar.setRoomName(getMeetingRoomName(event.getSummary()));
                   eventCalendar.setDescription(getDescription(event.getSummary()));
                   eventCalendar.setCreator(nameCreater);
                   listEvents.add(eventCalendar);
               }
               // sort follow roomName
               if (listEvents.size() > 0) {
                   Collections.sort(listEvents, new Comparator<EventCalendar>() {
                       @Override
                       public int compare(final EventCalendar object1, final EventCalendar object2) {
                           return object1.getRoomName().compareTo(object2.getRoomName());
                       }
                   });
               }
               calendarEvents.setListEvent(listEvents);
           }
           return calendarEvents;
       }catch (Exception ex){
           logger.error(ex.getMessage());
       }

       return null;
   }
   // get list calendar follow roomName
    public static CalendarEvents getListCalendarByRoomname(String roomName){
        CalendarEvents events = getCalendars();
        if(StringUtils.isNotEmpty(roomName)){
            List<EventCalendar> listEvent = new ArrayList<>();
            for (EventCalendar calendar: events.getListEvent()){
                if(calendar.getRoomName().equals(roomName)){
                   EventCalendar eventCalendar = new EventCalendar();
                    eventCalendar.setEventId(calendar.getEventId());
                    eventCalendar.setDescription(calendar.getDescription());
                    eventCalendar.setRoomName(calendar.getRoomName());
                    eventCalendar.setTimeFrom(calendar.getTimeFrom());
                    eventCalendar.setTimeTo(calendar.getTimeTo());
                    eventCalendar.setCreator(calendar.getCreator());
                    listEvent.add(eventCalendar);
                }
            }
           events.setListEvent(listEvent);
        }
        return events;
    }
    // get list room name from Calender event
    public static List<String> listRoomName(){
        CalendarEvents calendarEventsByRoom = getCalendars();
        List<String> getListRoomname = new ArrayList<>();
        Set<String> set =new HashSet<>();
        for (EventCalendar EventsRoom: calendarEventsByRoom.getListEvent()){
            if(set.add(EventsRoom.getRoomName())){
                getListRoomname.add(EventsRoom.getRoomName());
            }
        }
        return getListRoomname;
    }
    //get object event calendar
    public static EventCalendar getObjectEvent(String eventId){
        CalendarEvents calendarEvents = getCalendars();
        EventCalendar objectCalendar = new EventCalendar();
        for (EventCalendar eventCalendar: calendarEvents.getListEvent()){
            if(eventCalendar.getEventId().equals(eventId)){
                objectCalendar.setEventId(eventCalendar.getEventId());
                objectCalendar.setRoomName(eventCalendar.getRoomName());
                objectCalendar.setTimeFrom(eventCalendar.getTimeFrom());
                objectCalendar.setTimeTo(eventCalendar.getTimeTo());
                objectCalendar.setDescription(eventCalendar.getDescription());
            }
        }
        return objectCalendar;
    }
   public static String getMeetingRoomName(String value){
      try{
          String [] items = StringUtils.split(value,":");
          return items[0].toString();
      }catch (Exception ex){
          logger.error("Please check again format Summary ([NameRoom]:[Description]");
          return ex.getMessage();
      }

   }

    public static String getDescription(String value){
        try{
            String [] items = StringUtils.split(value,":");
            return items[1].toString();
        }catch (Exception ex){
            logger.error("Please check again format Summary ([NameRoom]:[Description]");
            return ex.getMessage();
        }
    }

    public static String getTimeFrom(DateTime dateTime){
        String [] timeFrom  = StringUtils.split(CommonUtils.valueParseDate(Constants.FORMATDATEHOURS, dateTime.toString())," ");
        return timeFrom[1];
    }
    public static String getTimeTo(DateTime dateTime){
        String [] timeTo  = StringUtils.split(CommonUtils.valueParseDate(Constants.FORMATDATEHOURS, dateTime.toString())," ");
        return timeTo[1];
    }
    public static String getCurrentDate(DateTime dateTime){
        String [] currentDate  = StringUtils.split(CommonUtils.valueParseDate(Constants.FORMATDATEHOURS, dateTime.toString())," ");
        return currentDate[0];
    }
    //insert event of calandar
    public static void insertEventCalendar(EventsDto eventsDto){
        try{
            Quickstart_Calendar.insertEvent(eventsDto);
        }catch (Exception ex){
            logger.error(ex.getMessage());
        }
    }
    //update event of calendar
    public static void updateEventCalendar(EventsDto eventsDto){
        try{
            Quickstart_Calendar.updateEvent(eventsDto);
        }catch (Exception ex){
            logger.error(ex.getMessage());
        }
    }
    //delete event of calendar
    public static void deleteEventCalendar(String calendarId, String eventId){
        try{
            Quickstart_Calendar.deleteEvent(calendarId, eventId);
        }catch (Exception ex){
            logger.error(ex.getMessage());
        }
    }

}
