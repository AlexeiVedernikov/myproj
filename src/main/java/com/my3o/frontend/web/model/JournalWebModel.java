package com.my3o.frontend.web.model;

import com.my3o.common.constant.MarkType;
import com.my3o.common.constant.Status;
import com.my3o.common.constant.TaskType;

import java.util.Date;

/**
 * @author Eugene Tuysus 21 июля 2014 г.
 * 
 */
public class JournalWebModel extends ReferenceBookModel {

    private String sheduleId;
    private String userId;
    private String date;
    private String mark;
    private MarkType markType;
    private TaskType taskType;

    public String getSheduleId() {
        return sheduleId;
    }

    public void setSheduleId(String sheduleId) {
        this.sheduleId = sheduleId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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
}
