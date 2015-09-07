package com.my3o.common.dto;

import com.my3o.backend.domain.SheduleEntity;
import com.my3o.backend.domain.UserEntity;
import com.my3o.common.constant.MarkType;
import com.my3o.common.constant.Status;
import com.my3o.common.constant.TaskType;

import java.util.Date;

/**
 * @author Rina 30/10/14
 * 
 */
public class JournalDto {
    private String id;
    private SheduleDto sheduleDto;
    private String date;
    private UserDto userDto;
    private String mark;
    private MarkType markType;
    private TaskType taskType;
    private String description;
    private Status status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public MarkType getMarkType() {
        return markType;
    }

    public void setMarkType(MarkType markType) {
        this.markType = markType;
    }

    public TaskType getTaskType() {
        return taskType;
    }

    public void setTaskType(TaskType taskType) {
        this.taskType = taskType;
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

    public SheduleDto getSheduleDto() {
        return sheduleDto;
    }

    public void setSheduleDto(SheduleDto sheduleDto) {
        this.sheduleDto = sheduleDto;
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }

    public JournalDto() {
    }

    public JournalDto(String id) {
        this.id = id;
    }
}
