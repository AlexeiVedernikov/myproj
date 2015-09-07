package com.my3o.backend.domain;

import com.my3o.common.constant.NotificationType;
import com.my3o.common.constant.NotificationUserType;
import com.my3o.common.constant.Status;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "SHEDULES")
@AttributeOverride(name = "id", column = @Column(name = "SHEDULE_ID", length = 32))
public class SheduleEntity extends BaseEntity {

    @ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH }, fetch = FetchType.LAZY)
    @JoinColumn(name = "DISCIPLINE_ID", insertable = true, updatable = true, nullable = false)
    private DisciplineEntity discipline;

    @ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH }, fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", insertable = true, updatable = true, nullable = false)
    private UserEntity user;

    @ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH }, fetch = FetchType.LAZY)
    @JoinColumn(name = "GROUP_ID", insertable = true, updatable = true, nullable = false)
    private GroupEntity group;

    @Column(nullable = false, length = 5, name = "YEAR")
    private String year;

    @Column(nullable = true, length = 32, name = "CABINET")
    private String cabinet;

    @Column(nullable = true, length = 32, name = "FROM_TIMES")
    private String fromTimes;

    @Column(nullable = true, length = 32, name = "TO_TIMES")
    private String toTimes;

    @Column(nullable = true, length = 255, name = "DESCRIPTION")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "STATUS")
    private Status status;

    public DisciplineEntity getDiscipline() {
        return discipline;
    }

    public void setDiscipline(DisciplineEntity discipline) {
        this.discipline = discipline;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public GroupEntity getGroup() {
        return group;
    }

    public void setGroup(GroupEntity group) {
        this.group = group;
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

    public String getFromTimes() {
        return fromTimes;
    }

    public void setFromTimes(String fromTimes) {
        this.fromTimes = fromTimes;
    }

    public String getToTimes() {
        return toTimes;
    }

    public void setToTimes(String toTimes) {
        this.toTimes = toTimes;
    }
}
