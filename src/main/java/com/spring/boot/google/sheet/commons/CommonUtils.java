package com.spring.boot.google.sheet.commons;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.spring.boot.google.sheet.dto.CalendarEvents;
import com.spring.boot.google.sheet.dto.EventCalendar;
import com.spring.boot.google.sheet.google_api.PaserDataCalendar;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Created by FRAMGIA\dang.ngoc.thien on 08/11/2016.
 */
public class CommonUtils {

    private static final Logger logger = LogManager.getLogger(CommonUtils.class);

    /*get properties
    * */

    public static String getPropertys(String properties){

        ResourceBundle resourceBundle = ResourceBundle.getBundle("customer_setting");

        String value = resourceBundle.getString(properties);

        return value;
    }


    /**
     * Check format date (dd/MM/yyyy)
     * @param value
     * @return boolean
     */
    public static boolean icValidateDateFormat(String value){
        SimpleDateFormat format = new SimpleDateFormat(Constants.FORMATDATE);
        try {
            format.parse(value);
            return true;
        }
        catch(ParseException e){
            return false;
        }
    }

    /*
    * Get current user information
    * */
    public static String getCurrentUserInfo() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }

    public static boolean hasRoles(String role) {
        Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>)
                SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        boolean hasRole = false;
        for (GrantedAuthority authority : authorities) {
            logger.info("authority : \t"+ authority.getAuthority());
            hasRole = authority.getAuthority().equals(role);
            if (hasRole) {
                break;
            }
        }
        return hasRole;
    }

    /*get time offset between time from and time to*/
    public static String getTimeOffset(String timeFrom, String timeTo){
        Calendar calendar = Calendar.getInstance();
        long longTimeFrom = new Date(timeTo).getTime();
        long longTimeTo =  new Date(timeFrom).getTime();
        long timeOffset = longTimeFrom - longTimeTo ;
        String diffMinutes = String.valueOf(timeOffset / (60 * 1000) % 60);
        String diffHours = String.valueOf(timeOffset / (60 * 60 * 1000) % 24);
        return diffHours+","+diffMinutes;
    }
    /*Set time OO:00:00 today*/
    public static long getTimeToday(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.AM_PM, 0);
        calendar.set(Calendar.HOUR, 00);
        calendar.set(Calendar.MINUTE, 00);
        calendar.set(Calendar.SECOND, 00);
        return calendar.getTimeInMillis();
    }
    /*paser date from google api calendar*/
    public static String valueParseDate(String preFormat, String dateTime){
        try {
            SimpleDateFormat sd2 = new SimpleDateFormat(preFormat);
            SimpleDateFormat sd1 = new SimpleDateFormat(Constants.DATE_FORMAT_PATTERN_X);
            Date date =  sd1.parse(dateTime.trim());
            return sd2.format(date);
        } catch (ParseException e) {
            return e.getMessage();
        }
    }
    /*paser date from new Date to yyyy-MM-dd'T'HH:mm:ss.SSSX => 2016-11-22T15:30:00.000+07:00*/
    public static String paserDateToFormatUTC(String time){
        SimpleDateFormat formatStander = new SimpleDateFormat(Constants.DATE_FORMAT_PATTERN_Z);
        String [] arrayTime = StringUtils.split(time, ":");
        Integer hour = Integer.valueOf(arrayTime[0]);
        Integer minute = Integer.valueOf(arrayTime[1]);
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.AM_PM, 1);
        calendar.set(Calendar.HOUR_OF_DAY,hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, 00);
        return formatStander.format(calendar.getTime()).concat("+07:00");
    }
    // paser new Date from time string
    public static long paserLongDate(String time){
        String [] arrayTimeFrom = StringUtils.split(time, ":");
        Date date = new Date();
        date.setHours(Integer.valueOf(arrayTimeFrom[0]));
        date.setMinutes(Integer.valueOf(arrayTimeFrom[1]));
        return date.getTime();
    }
    //check range hour and minute for booking room meeting each day
    public static boolean checkRangeHourMinute(String timeFrom, String timeTo, String roomName){
        CalendarEvents calendarEvents = PaserDataCalendar.getCalendars();
        long dateFrom = paserLongDate(timeFrom);
        long dateTo= paserLongDate(timeTo);

       for (EventCalendar eventCalendar: calendarEvents.getListEvent()){
           if(eventCalendar.getRoomName().contains(roomName))
               if((dateFrom >= paserLongDate(eventCalendar.getTimeFrom()) && dateTo <= paserLongDate(eventCalendar.getTimeTo()))
                       || (dateFrom < paserLongDate(eventCalendar.getTimeFrom()) && dateTo > paserLongDate(eventCalendar.getTimeFrom()))
                       || (dateFrom < paserLongDate(eventCalendar.getTimeTo()) && dateTo > paserLongDate(eventCalendar.getTimeTo()))
                       || (dateFrom < paserLongDate(eventCalendar.getTimeFrom()) && dateTo > paserLongDate(eventCalendar.getTimeTo()))){
                   return false;
               }
       }
       return true;
    }

   /* public static void main (String arg[]){
        *//*DateTime dateTime = new DateTime("2016-11-22T15:00:00.000+07:00");*//*
        System.out.printf("%s", paserDateToFormatUTC("19:00"));
    }*/

}
