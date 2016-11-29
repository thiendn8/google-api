package com.spring.boot.google.sheet.repository;

import com.spring.boot.google.sheet.model.RequestTimeSheet;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by FRAMGIA\dang.ngoc.thien on 14/11/2016.
 */
public interface TimeSheetRepository extends CrudRepository<RequestTimeSheet, Long> {

    List<RequestTimeSheet> getRequestTimeSheetListByCode(String code);


    @Modifying
    @Query(value = "update cre_timesheet set status = 'Approved' where id = ?1 ", nativeQuery = true)
    @Transactional
    void updateStatusApprove(Integer id);


    @Query(value = "select * FROM cre_timesheet cre_ where cre_.status like 'Approved' and cre_.code = ?1", nativeQuery = true)
    List<RequestTimeSheet> getListRequestApproveByCode(String code);

}
