package com.my3o.frontend.web.model;

import com.my3o.common.constant.NotificationType;
import com.my3o.common.constant.NotificationUserType;

import java.util.Date;

public class SheduleWebModel extends ReferenceBookModel {

    private String disciplineId;
    private String userId;
    private String groupId;
    private String year;
    private String cabinet;
    private String fromTimes;
    private String toTimes;

    public String getDisciplineId() {
        return disciplineId;
    }

    public void setDisciplineId(String disciplineId) {
        this.disciplineId = disciplineId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getCabinet() {
        return cabinet;
    }

    public void setCabinet(String cabinet) {
        this.cabinet = cabinet;
    }

    public String getFromTimes() { return fromTimes; }

    public void setFromTimes(String fromTimes) { this.fromTimes = fromTimes; }

    public String getToTimes() { return toTimes; }

    public void setToTimes(String toTimes) { this.toTimes = toTimes; }
}
