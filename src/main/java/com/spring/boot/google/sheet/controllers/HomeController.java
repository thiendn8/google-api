package com.spring.boot.google.sheet.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.boot.google.sheet.commons.CommonUtils;
import com.spring.boot.google.sheet.commons.StringUtils;
import com.spring.boot.google.sheet.dto.EventCalendar;
import com.spring.boot.google.sheet.dto.EventsDto;
import com.spring.boot.google.sheet.google_api.PaserDataCalendar;
import com.spring.boot.google.sheet.model.RequestTimeSheet;
import com.spring.boot.google.sheet.repository.RoleRepository;
import com.spring.boot.google.sheet.service.IDataTimeSheetService;
import com.spring.boot.google.sheet.service.ITimesheet;
import com.spring.boot.google.sheet.validator.UserValidator;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Created by FRAMGIA\dang.ngoc.thien on 08/11/2016.
 */
@Controller
public class HomeController {

    static final Logger logger = LogManager.getLogger(HomeController.class);


    private IDataTimeSheetService dataTimeSheetService;

    @Autowired
    private UserValidator userValidator;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private ITimesheet iTimesheet;

    @Autowired
    public void setPersonService(IDataTimeSheetService personService){
        this.dataTimeSheetService = personService;
    }

    /*
    * go home
    * */

    @RequestMapping(value = {"/", "/welcome"}, method = RequestMethod.GET)
    public String welcome(Model model, @RequestParam(value = "sheetName", required = false, defaultValue = "") String sheetName) {
        logger.info(CommonUtils.getCurrentUserInfo());
        logger.info("Role of user:"+CommonUtils.hasRoles("Manager"));
        if(CommonUtils.hasRoles("Manager"))
            return "redirect:settingService";//redirect setting service
        model.addAttribute("listMonth", dataTimeSheetService.getListMonth());
        model.addAttribute("employeeDetail", dataTimeSheetService.getEmployeeDetail(sheetName));
        model.addAttribute("indexSelect", sheetName);
        if(iTimesheet.getListRequestApproveByCode(CommonUtils.getCurrentUserInfo()).size() > 0)
            model.addAttribute("listRequestApprove", iTimesheet.getListRequestApproveByCode(CommonUtils.getCurrentUserInfo()));
        else
            model.addAttribute("listRequestApprove", null);

        return "welcome";
    }
    /*
    * Mapping for Post request timesheet
    * @param: object timeSheet
    * @return
    * */

    @RequestMapping(value = "/createRequest", method = RequestMethod.POST)
    public String registration(@ModelAttribute("timeSheetForm") RequestTimeSheet timeSheet, Model model) {

       try{
           logger.debug(timeSheet.toString());
           timeSheet.setTimeOffset(CommonUtils.getTimeOffset(timeSheet.getTimeFrom(), timeSheet.getTimeTo()));
           iTimesheet.save(timeSheet);
           model.addAttribute("name", timeSheet.getName());
           return "success";
       }catch (Exception ex){
        logger.error(ex.getMessage());
       }
        return "error";
    }
    /*
    * Mapping for go to My request timesheet
    * */
    @RequestMapping(value = "/myRequestTimeSheet", method = RequestMethod.GET)
    public String myRequestTimeSheet(Model model){
        model.addAttribute("listMyRequest", iTimesheet.getListMyRequest(CommonUtils.getCurrentUserInfo()));
        return "timesheets/myRequestTimes";
    }
    /*
    * Mapping for go to My request timesheet
    * */
    @PreAuthorize("hasAnyRole('Manager') or hasAnyRole('Approver')")
    @RequestMapping(value = "/approveTimesheet", method = RequestMethod.GET)
    public String approveTimesheet(Model model){
        if(CommonUtils.hasRoles("Manager") || CommonUtils.hasRoles("Approver"))
            model.addAttribute("listMyRequest", iTimesheet.getAllListRequest());
        else
            model.addAttribute("listMyRequest", iTimesheet.getListMyRequest(CommonUtils.getCurrentUserInfo()));
        return "timesheets/approveTimesheet";
    }
    /*
    * Maping for approved time sheet
    * */
    @PreAuthorize("hasAnyRole('Manager') or hasAnyRole('Approver')")
    @RequestMapping(value = "/processApproved", method = RequestMethod.GET)
    public String approved(@RequestParam(name = "indexRequest", required = true) Integer indexRequest){
        try{
          logger.info("isStatus :\t"+ indexRequest);
            iTimesheet.updateStatusApprove(indexRequest);
            return "success";
        }catch (Exception ex){
            logger.error(ex.getMessage());
        }
        return "error";
    }
    /*
    * Mapping for list booking meeting room
    * */
    @PreAuthorize("hasAnyRole('Manager') or hasAnyRole('Approver')")
    @RequestMapping(value = "/checkTime", method = RequestMethod.GET)
    @ResponseBody
    public String checkTime(
            @RequestParam(name = "timeFrom", required = true)String timeFrom,
            @RequestParam(name = "timeTo", required = true) String timeTo,
            @RequestParam(name = "roomName", required = true) String roomName){
        if(StringUtils.isNotEmpty(timeFrom) && StringUtils.isNotEmpty(timeTo) && StringUtils.isNotEmpty(roomName)){
            if(CommonUtils.checkRangeHourMinute(timeFrom, timeTo, roomName))
                return "00";
        }
        return "01";
    }
    /*
    * Mapping for list booking meeting room
    * */
    @PreAuthorize("hasAnyRole('Manager') or hasAnyRole('Approver')")
    @RequestMapping(value = "/listBookingRoom", method = RequestMethod.GET)
    public String getListBookingMeetingRoom(Model model,
                                            @RequestParam(name = "roomName", required = true, defaultValue = "") String roomName){
        model.addAttribute("calendarMeeting", PaserDataCalendar.getListCalendarByRoomname(roomName));
        model.addAttribute("listRoomname", PaserDataCalendar.listRoomName());
        return"room_meeting/listMeetingRoom";
    }
    /*
    * Mapping for remove booking meeting room
    * */
    @PreAuthorize("hasAnyRole('Manager') or hasAnyRole('Approver')")
    @RequestMapping(value = "/removeBooking", method = RequestMethod.POST)
    public String removeBookingMeetingRoom(@ModelAttribute(value = "eventDtos")EventsDto eventsDto){
        PaserDataCalendar.deleteEventCalendar(CommonUtils.getPropertys("calendar.calendarId"),eventsDto.getEventId());
        return"redirect:listBookingRoom";
    }
    /*
    * Mapping for list booking meeting room
    * */
    @PreAuthorize("hasAnyRole('Manager') or hasAnyRole('Approver')")
    @RequestMapping(value = "/saveOrUpdateBookingRoom", method = RequestMethod.POST)
    public String saveOrUpdateBookingRoom(Model model, @ModelAttribute(value = "eventDto")EventsDto eventsDto){
        logger.info(eventsDto);
        eventsDto.setStartTime(CommonUtils.paserDateToFormatUTC(eventsDto.getStartTime()));
        eventsDto.setEndTime(CommonUtils.paserDateToFormatUTC(eventsDto.getEndTime()));
        eventsDto.setCalendarId(CommonUtils.getPropertys("calendar.calendarId"));
        eventsDto.setTimeZone(CommonUtils.getPropertys("calendar.timeZone"));
        eventsDto.setCreator(CommonUtils.getCurrentUserInfo());

        if(StringUtils.isEmpty(eventsDto.getEventId()))
            PaserDataCalendar.insertEventCalendar(eventsDto);
        else
            PaserDataCalendar.updateEventCalendar(eventsDto);

        return"redirect:listBookingRoom";
    }

    // update booking room calendar follow eventId
    @PreAuthorize("hasAnyRole('Manager') or hasAnyRole('Approver')")
    @RequestMapping(value = "/updateBookingRoom", method = RequestMethod.GET)
    @ResponseBody
    public String updateBookingRoom(@RequestParam(name = "eventId", required = true) String eventId){
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            String datajson = objectMapper.writerWithView(EventCalendar.class).
                   writeValueAsString(PaserDataCalendar.getObjectEvent(eventId));
          return datajson;
        }catch (Exception ex){
            logger.error(ex.getMessage());
        }
        return "01";
    }
}
