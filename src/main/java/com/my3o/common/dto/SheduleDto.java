package com.my3o.common.dto;

import com.my3o.common.constant.NotificationType;
import com.my3o.common.constant.NotificationUserType;
import com.my3o.common.constant.Status;

import java.util.Date;

/**
 * Created by chudoshilkin
 */
public class SheduleDto {
    private String id;
    private DisciplineDto disciplineDto;
    private UserDto userDto;
    private GroupDto groupDto;
    private String year;
    private String cabinet;
    private String fromTimes;
    private String toTimes;
    private String description;
    private Status status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public DisciplineDto getDisciplineDto() {
        return disciplineDto;
    }

    public void setDisciplineDto(DisciplineDto disciplineDto) {
        this.disciplineDto = disciplineDto;
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }

    public GroupDto getGroupDto() {
        return groupDto;
    }

    public void setGroupDto(GroupDto groupDto) {
        this.groupDto = groupDto;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getFromTimes() { return fromTimes; }

    public void setFromTimes(String fromTimes) { this.fromTimes = fromTimes; }

    public String getToTimes() { return toTimes; }

    public void setToTimes(String toTimes) { this.toTimes = toTimes; }

    public SheduleDto() {
    }

    public SheduleDto(String id) {
        this.id = id;
    }
}
