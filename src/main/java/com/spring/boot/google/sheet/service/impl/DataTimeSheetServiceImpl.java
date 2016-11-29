package com.spring.boot.google.sheet.service.impl;

import com.spring.boot.google.sheet.commons.Months;
import com.spring.boot.google.sheet.dto.Employees;
import com.spring.boot.google.sheet.google_api.PaserDataSheetApi;
import com.spring.boot.google.sheet.model.Person;
import com.spring.boot.google.sheet.repository.RepositoryDao;
import com.spring.boot.google.sheet.service.IDataTimeSheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by FRAMGIA\dang.ngoc.thien on 08/11/2016.
 */
@Service
public class DataTimeSheetServiceImpl implements IDataTimeSheetService {

    private RepositoryDao dao;
    private static List<String> listMonth = null;

    @Autowired
    public void setRepostoryDao(RepositoryDao repostoryDao){
        this.dao = repostoryDao;
    }

    @Override
    public List<String> getListMonth() {
        listMonth = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        int month = calendar.get(Calendar.MONTH)+1;
        int yearCur = calendar.get(Calendar.YEAR);
        calendar.add(Calendar.MONTH, -12);

        for (int i = month; i >= (month - 12); i--){
            switch (i){
                case 12 :{
                    listMonth.add(String.valueOf(Months.Dec)+(yearCur));
                    continue;
                }
                case 11 :{
                    listMonth.add(String.valueOf(Months.Nov)+(yearCur));
                    continue;
                }
                case 10 :{
                    listMonth.add(String.valueOf(Months.Oct)+(yearCur));
                    continue;
                }
                case 9 :{
                    listMonth.add(String.valueOf(Months.Sep)+(yearCur));
                    continue;
                }
                case 8 :{
                    listMonth.add(String.valueOf(Months.Aug)+(yearCur));
                    continue;
                }
                case 7 :{
                    listMonth.add(String.valueOf(Months.Jul)+(yearCur));
                    continue;
                }
                case 6 :{
                    listMonth.add(String.valueOf(Months.Jun)+(yearCur));
                    continue;
                }
                case 5 :{
                    listMonth.add(String.valueOf(Months.May)+(yearCur));
                    continue;
                }
                case 4 :{
                    listMonth.add(String.valueOf(Months.Apr)+(yearCur));
                    continue;
                }
                case 3 :{
                    listMonth.add(String.valueOf(Months.Mar)+(yearCur));
                    continue;
                }
                case 2 :{
                    listMonth.add(String.valueOf(Months.Feb)+(yearCur));
                    continue;
                }
                case 1 :{
                    listMonth.add(String.valueOf(Months.Jun)+(yearCur));
                    continue;
                }
                case 0 :{
                    listMonth.add(String.valueOf(Months.Dec)+(yearCur-1));
                    continue;
                }
                case -1:{
                    listMonth.add(String.valueOf(Months.Nov)+(yearCur-1));
                    continue;
                }
                case -2 :{
                    listMonth.add(String.valueOf(Months.Oct)+(yearCur-1));
                    continue;
                }
                case -3 :{
                    listMonth.add(String.valueOf(Months.Sep)+(yearCur-1));
                    continue;
                }
                case -4 :{
                    listMonth.add(String.valueOf(Months.Aug)+(yearCur-1));
                    continue;
                }
                case -5 :{
                    listMonth.add(String.valueOf(Months.Jul)+(yearCur-1));
                    continue;
                }
                case -6 :{
                    listMonth.add(String.valueOf(Months.Jun)+(yearCur-1));
                    continue;
                }
                case -7 :{
                    listMonth.add(String.valueOf(Months.May)+(yearCur-1));
                    continue;
                }
                case -8 :{
                    listMonth.add(String.valueOf(Months.Apr)+(yearCur-1));
                    continue;
                }
                case -9 :{
                    listMonth.add(String.valueOf(Months.Mar)+(yearCur-1));
                    continue;
                }
                case -10 :{
                    listMonth.add(String.valueOf(Months.Feb)+(yearCur-1));
                    continue;
                }
                case -11 :{
                    listMonth.add(String.valueOf(Months.Jan)+(yearCur-1));
                    continue;
                }
            }
        }
        return listMonth;
    }

    @Override
    public Employees getEmployeeDetail(String sheetName) {
        if(sheetName.isEmpty()){
            sheetName = listMonth.get(0);
        }
        return PaserDataSheetApi.getEmployeDetail(sheetName);
    }

    @Override
    public List<Person> getListPerson() {
        return dao.getListPerson();
    }


}


