package com.spring.boot.google.sheet.google_api;

import com.spring.boot.google.sheet.commons.CommonUtils;
import com.spring.boot.google.sheet.dto.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by FRAMGIA\dang.ngoc.thien on 09/11/2016.
 */
public class PaserDataSheetApi {
    public static List<List<Object>> lists = null;
    public static Employees getEmployeDetail = null;
    public static   List<DateByMonth> listDateMonth = null;

    /*
    * get list all data employees
    * */
    public static Employees getEmployeDetail(String sheetName) {
        try {
            lists = Quickstart.getAllDataBySheetName(sheetName);
            //get date time with row A1
            listDateMonth = new ArrayList<>();
            for (int i = 0; i < lists.size(); i++) {

                //get date time with row A1
                if (i == 0) {
                    List<Object> getListDate = lists.get(i);
                    for (int j = 0; j < getListDate.size(); j++) {
                        if (j % 2 == 0) {
                            if (CommonUtils.icValidateDateFormat(getListDate.get(j).toString()))
                                listDateMonth.add(new DateByMonth(getListDate.get(j).toString()));
                        }
                    }
                }

                //date code, schedule with row A3
                if (i >= 2 && lists.get(i).get(0).equals(CommonUtils.getCurrentUserInfo())) {
                    getEmployeDetail = new Employees();

                    List<Object> getListData = lists.get(i);

                    StaffAndCode staffAndCode = new StaffAndCode();
                    List<ScheduleDateIO> scheduleDateIOList = new ArrayList<>();
                    String timeIn = "";
                    String timeOut = "";
                    int indexAddTime = 0;
                    int indexListDate = 0;
                    //get single employee data
                    for (int k = 0; k < (listDateMonth.size() * 2); k++) {
                        EntityTimes entityTimes = new EntityTimes();
                        ScheduleDateIO scheduleDateIO = new ScheduleDateIO();
                        //convert data for code and staff
                        if (k < 2) {
                            if (k == 0)
                                staffAndCode.setEmployeeCode(getListData.get(k).toString());
                            else
                                staffAndCode.setStaffName(getListData.get(k).toString());
                        } else {
                            //get timeIn and timeOut
                            if (k % 2 == 0) {
                                indexAddTime++;
                                timeIn = getListData.get(k).toString();
                            } else {
                                indexAddTime--;
                                timeOut = getListData.get(k).toString();
                            }
                            if (indexAddTime % 2 == 0) {
                                entityTimes.setTimeIn(timeIn);
                                entityTimes.setTimeout(timeOut);
                                scheduleDateIO.setEntityTimes(entityTimes);

                                if ((k % 2) < listDateMonth.size()) {
                                    scheduleDateIO.setDateTime(listDateMonth.get(indexListDate).getDateTime().toString());
                                }
                                scheduleDateIOList.add(scheduleDateIO);
                                indexListDate++;
                            }
                        }
                    }
                    //set Employees
                    getEmployeDetail.setStaffAndCode(staffAndCode);
                    getEmployeDetail.setListTimes(scheduleDateIOList);
                }
            }
            return getEmployeDetail;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

/*    *//*get date month of timesheet employee*/
    public static List<DateByMonth> getListDateMonth() {
        List<List<Object>> lists = null;
        try {
            lists = Quickstart.getListDate();

            List<DateByMonth> listDateMonth = new ArrayList<>();
            for (int i = 0; i < lists.size(); i++) {
                //get date time with row A1
                if (i == 0) {
                    List<Object> getListDate = lists.get(i);
                    for (int j = 0; j < getListDate.size(); j++) {
                        if (j % 2 == 0) {
                            if (CommonUtils.icValidateDateFormat(getListDate.get(j).toString()))
                                listDateMonth.add(new DateByMonth(getListDate.get(j).toString()));
                        }
                    }
                }
            }
            return listDateMonth;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }



  /*  public static void main(String arg[]){
        System.out.println(getListDateMonth());
//        getEmployeDetail("B120731");
    }*/
}