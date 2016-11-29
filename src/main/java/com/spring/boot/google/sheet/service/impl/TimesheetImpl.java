package com.spring.boot.google.sheet.service.impl;

import com.spring.boot.google.sheet.model.RequestTimeSheet;
import com.spring.boot.google.sheet.repository.TimeSheetRepository;
import com.spring.boot.google.sheet.service.ITimesheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by FRAMGIA\dang.ngoc.thien on 14/11/2016.
 */
@Service
public class TimesheetImpl implements ITimesheet {

    @Autowired
    private TimeSheetRepository timeSheetRepository;

    @Override
    public List<RequestTimeSheet> getListMyRequest(String code) {
        return timeSheetRepository.getRequestTimeSheetListByCode(code);
    }

    @Override
    public void save(RequestTimeSheet requestTimeSheet) {
        timeSheetRepository.save(requestTimeSheet);
    }

    @Override
    public void updateStatusApprove(Integer index) {
        timeSheetRepository.updateStatusApprove(index);
    }

    @Override
    public List<RequestTimeSheet> getAllListRequest() {
        return (List<RequestTimeSheet>) timeSheetRepository.findAll();
    }

    @Override
    public List<RequestTimeSheet> getListRequestApproveByCode(String code) {
        return timeSheetRepository.getListRequestApproveByCode(code);
    }
}
