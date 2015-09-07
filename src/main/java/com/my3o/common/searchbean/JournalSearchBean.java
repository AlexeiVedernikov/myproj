package com.my3o.common.searchbean;

import com.my3o.common.constant.MarkType;
import com.my3o.common.constant.Status;
import com.my3o.common.constant.TaskType;
import com.my3o.common.dto.SheduleDto;
import com.my3o.common.dto.UserDto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Rina 30/10/14
 * 
 */
public class JournalSearchBean extends AbstractSearchBean<String> {

    private List<String> sheduleIdList = new ArrayList<String>();
    private String userId;
    private String mark;
    private MarkType markType;
    private TaskType taskType;
    private String description;
    private Status status;
    private String date;
    private List<String> dateList = new ArrayList<String>();

    public List<String> getSheduleIdList() {
        return sheduleIdList;
    }

    public void addSheduleId (String sheduleId) {
        this.sheduleIdList.add(sheduleId);
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public List<String> getDateList() {
        return dateList;
    }

    public void addDate(String date) {
        this.dateList.add(date);
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
