package com.my3o.backend.domain;

import com.my3o.common.constant.MarkType;
import com.my3o.common.constant.Status;
import com.my3o.common.constant.TaskType;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "JOURNAL")
@AttributeOverride(name = "id", column = @Column(name = "JOURNAL_ID", length = 32))
public class JournalEntity extends BaseEntity {

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "SHEDULE_ID", insertable = true, updatable = true, nullable = false)
    private SheduleEntity shedule;

    @Column(nullable = false, length = 16, name = "DATE")
    private String date;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", insertable = true, updatable = true, nullable = false)
    private UserEntity user;

    @Column(nullable = false, length = 32, name = "MARK")
    private String mark;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "MARK_TYPE")
    private MarkType markType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "TASK_TYPE")
    private TaskType taskType;

    @Column(nullable = true, length = 255, name = "DESCRIPTION")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "STATUS")
    private Status status;

    public SheduleEntity getShedule() {
        return shedule;
    }

    public void setShedule(SheduleEntity shedule) {
        this.shedule = shedule;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
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
}
