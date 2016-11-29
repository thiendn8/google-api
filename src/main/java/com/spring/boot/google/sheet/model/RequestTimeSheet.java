package com.spring.boot.google.sheet.model;

import com.spring.boot.google.sheet.commons.StringUtils;

import javax.persistence.*;

/**
 * Created by FRAMGIA\dang.ngoc.thien on 14/11/2016.
 */
@Entity
@Table(name = "cre_timesheet")
public class RequestTimeSheet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, updatable = false)
    private Integer id;
    @Column(name = "code")
    private String code;
    @Column(name = "name")
    private String name;
    @Column(name = "type_request")
    private String typeRequest;
    @Column(name = "time_from")
    private String timeFrom;
    @Column(name = "time_to")
    private String timeTo;
    @Column(name = "reason_request")
    private String reasonRequest;
    @Column(name = "group_lead")
    private String groupLead;
    @Column(name = "team_lead")
    private String teamLead;
    @Column(name = "status")
    private String status="Pending";
    @Column(name = "time_offset")
    private String timeOffset;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTypeRequest() {
        return typeRequest;
    }

    public void setTypeRequest(String typeRequest) {
        this.typeRequest = typeRequest;
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

    public String getReasonRequest() {
        return reasonRequest;
    }

    public void setReasonRequest(String reasonRequest) {
        this.reasonRequest = reasonRequest;
    }

    public String getGroupLead() {
        return groupLead;
    }

    public void setGroupLead(String groupLead) {
        this.groupLead = groupLead;
    }

    public String getTeamLead() {
        return teamLead;
    }

    public void setTeamLead(String teamLead) {
        this.teamLead = teamLead;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTimeOffset() {
        return timeOffset;
    }

    public void setTimeOffset(String timeOffset) {
        this.timeOffset = timeOffset;
    }

    @Override
    public String toString() {
        return "RequestTimeSheet{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", typeRequest='" + typeRequest + '\'' +
                ", timeFrom='" + timeFrom + '\'' +
                ", timeTo='" + timeTo + '\'' +
                ", reasonRequest='" + reasonRequest + '\'' +
                ", groupLead='" + groupLead + '\'' +
                ", teamLead='" + teamLead + '\'' +
                ", status='" + status + '\'' +
                ", timeOffset='" + timeOffset + '\'' +
                '}';
    }
}
