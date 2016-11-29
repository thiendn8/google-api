package com.spring.boot.google.sheet.service;

import com.spring.boot.google.sheet.model.RequestTimeSheet;

import java.util.List;

/**
 * Created by FRAMGIA\dang.ngoc.thien on 14/11/2016.
 */
public interface ITimesheet {
    void save(RequestTimeSheet requestTimeSheet);

    List<RequestTimeSheet> getListMyRequest(String code);

    void updateStatusApprove(Integer index);

    List<RequestTimeSheet> getAllListRequest();


    List<RequestTimeSheet> getListRequestApproveByCode(String code);

}
