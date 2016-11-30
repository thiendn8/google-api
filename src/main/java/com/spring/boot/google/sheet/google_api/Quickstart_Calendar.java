package com.spring.boot.google.sheet.google_api;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.*;
import com.spring.boot.google.sheet.commons.CommonUtils;
import com.spring.boot.google.sheet.commons.StringUtils;
import com.spring.boot.google.sheet.dto.EventsDto;
import org.apache.commons.logging.impl.SimpleLog;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

public class Quickstart_Calendar {

    private static final Logger logger = LogManager.getLogger(Quickstart_Calendar.class);
    /** Application name. */
    private static final String APPLICATION_NAME =
            "Google API Quickstart_Calendar";

    /** Directory to store user credentials for this application. */
    private static final java.io.File DATA_STORE_DIR = new java.io.File(
            System.getProperty("user.home"), ".credentials/calendar-java-quickstart");

    /** Global instance of the {@link FileDataStoreFactory}. */
    private static FileDataStoreFactory DATA_STORE_FACTORY;

    /** Global instance of the JSON factory. */
    private static final JsonFactory JSON_FACTORY =
            JacksonFactory.getDefaultInstance();

    /** Global instance of the HTTP transport. */
    private static HttpTransport HTTP_TRANSPORT;

    /** Global instance of the scopes required by this quickstart.
     *
     * If modifying these scopes, delete your previously saved credentials
     * at ~/.credentials/calendar-java-quickstart
     */
    private static final List<String> SCOPES =
            Arrays.asList(CalendarScopes.CALENDAR);

    static {
        try {
            HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
            DATA_STORE_FACTORY = new FileDataStoreFactory(DATA_STORE_DIR);
        } catch (Throwable t) {
            t.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Creates an authorized Credential object.
     * @return an authorized Credential object.
     * @throws IOException
     */
    public static Credential authorize() throws IOException {
        // Load client secrets.
        InputStream in =
                Quickstart_Calendar.class.getResourceAsStream("/client_secret.json");
        GoogleClientSecrets clientSecrets =
                GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow =
                new GoogleAuthorizationCodeFlow.Builder(
                        HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                        .setDataStoreFactory(DATA_STORE_FACTORY)
                        .setAccessType("offline")
                        .build();
        Credential credential = new AuthorizationCodeInstalledApp(
                flow, new LocalServerReceiver()).authorize("ngocthienbear@gmail.com");
        logger.info(
                "Credentials saved to " + DATA_STORE_DIR.getAbsolutePath());
        return credential;
    }

    /**
     * Build and return an authorized Calendar client service.
     * @return an authorized Calendar client service
     * @throws IOException
     */
    public static com.google.api.services.calendar.Calendar
    getCalendarService() throws IOException {
        Credential credential = authorize();
        return new com.google.api.services.calendar.Calendar.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, credential)
                .setApplicationName(APPLICATION_NAME)
                .build();
    }
    /*
    * Get list event calendar of user from 00:00:00 - 23:59:59 on current date
    * */

    public static Events getListEvents(){
        Events events = null;
        try{
            // Build a new authorized API client service.
            // Note: Do not confuse this class with the
            //   com.google.api.services.calendar.model.Calendar class.
            com.google.api.services.calendar.Calendar service =
                    getCalendarService();

            // List the next 10 events from the primary calendar.
             events = service.events().list("primary")
                    .setTimeMin(new DateTime(CommonUtils.getTimeToday()))
                    .setOrderBy("startTime")
                    .setSingleEvents(true)
                    .execute();
        }catch (Exception ex){
           logger.error("(%)\t"+ ex.getMessage());
        }
        return events;
    }

    /*
    * Insert Event of calendar
    * @Object: eventObject
    * @return: void
    * */

    public static void insertEvent(EventsDto eventsDto){
        com.google.api.services.calendar.Calendar service = null;
        try {
            service = getCalendarService();
            Event event = new Event()
                    .setSummary(eventsDto.getSummary())
                    .setLocation(eventsDto.getAddress())
                    .setDescription(eventsDto.getDescription());
            //2016-11-22T15:30:00.000+07:00
            DateTime startDateTime = new DateTime(eventsDto.getStartTime());
            EventDateTime start = new EventDateTime()
                    .setDateTime(startDateTime)
                    .setTimeZone(eventsDto.getTimeZone());
            event.setStart(start);

            DateTime endDateTime = new DateTime(eventsDto.getEndTime());
            EventDateTime end = new EventDateTime()
                    .setDateTime(endDateTime)
                    .setTimeZone(eventsDto.getTimeZone());
            event.setEnd(end);

            String[] recurrence = new String[] {"RRULE:FREQ=DAILY;COUNT=1"};
            event.setRecurrence(Arrays.asList(recurrence));
            String [] listEmailInform = StringUtils.split(eventsDto.getMailInfrom(), ",");
            if(listEmailInform.length > 0){
                EventAttendee[] attendees;
                for (String email: listEmailInform){
                    attendees= new EventAttendee[] {
                            new EventAttendee().setEmail(email),
                    };
                    event.setAttendees(Arrays.asList(attendees));
                }
            }
            EventReminder[] reminderOverrides = new EventReminder[] {
                    new EventReminder().setMethod("email").setMinutes(10),
                    //new EventReminder().setMethod("email").setMinutes(24 * 60),
                    new EventReminder().setMethod("popup").setMinutes(10),
            };
            Event.Reminders reminders = new Event.Reminders()
                    .setUseDefault(false)
                    .setOverrides(Arrays.asList(reminderOverrides));
            event.setReminders(reminders);
            event = service.events().insert(eventsDto.getCalendarId(), event).execute();

            logger.info("Event created: %s\n"+ event.getHtmlLink());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    /*
    * Update event for calendar(event must to exist in list calendar)
    * @param: EventDto
    * @return: void    *
    * */

    public static void updateEvent(EventsDto eventsDto){
        // Build a new authorized API client service.
        com.google.api.services.calendar.Calendar service = null;
        try {
            service = getCalendarService();
            // Retrieve the event from the API
            /*Event event = service.events().get("primary", "eventId").execute();*/
            Event event = service.events().get(eventsDto.getCalendarId(), eventsDto.getEventId()).execute();

            // Make a change
            event.setSummary(eventsDto.getSummary());
            //2016-11-22T15:30:00.000+07:00
            DateTime startDateTime = new DateTime(eventsDto.getStartTime());
            EventDateTime start = new EventDateTime()
                    .setDateTime(startDateTime)
                    .setTimeZone(eventsDto.getTimeZone());
            event.setStart(start);

            DateTime endDateTime = new DateTime(eventsDto.getEndTime());
            EventDateTime end = new EventDateTime()
                    .setDateTime(endDateTime)
                    .setTimeZone(eventsDto.getTimeZone());
            event.setEnd(end);

            // Update the event
            Event updatedEvent = service.events().update(eventsDto.getCalendarId(), event.getId(), event).execute();

            logger.info("Process \t"+updatedEvent.getUpdated());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
   /*
    * Delete event for calendar(event must to exist in list calendar)
    * @param: calendarId
    * @param: eventId
    * @return: void
    * */

    public static void deleteEvent(String calendarId, String eventId){
        // Build a new authorized API client service.
        com.google.api.services.calendar.Calendar service = null;
        try {
            service = getCalendarService();
            // Delete an event
            service.events().delete(calendarId, eventId).execute();
            logger.info("Process \t"+ service.events().delete(calendarId,eventId).execute());
        } catch (IOException e) {
            e.printStackTrace();
        }


    }



}