package com.my3o.backend.domain;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.my3o.common.constant.NotificationType;
import com.my3o.common.constant.NotificationUserType;
import com.my3o.common.constant.Status;

@Entity
@Table(name = "NOTIFICATION")
@AttributeOverride(name = "id", column = @Column(name = "NOTIFICATION_ID", length = 32))
public class NotificationEntity extends BaseComplianceEntity {

    @Column(nullable = false, length = 255, name = "NAME")
    private String name;

    @Column(nullable = true, length = 255, name = "DESCRIPTION")
    private String description;

    @Column(nullable = true, name = "TEXT", columnDefinition = "MEDIUMTEXT")
    private String text;

    @Column(nullable = true, name = "DATE_ON")
    private Date dateOn;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "TYPE_NOTIFICATION")
    private NotificationType notificationType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "STATUS")
    private Status status;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "USER_TYPE")
    private NotificationUserType userType;

    @ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH }, fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", insertable = true, updatable = true, nullable = false)
    private UserEntity user;

    @ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH }, fetch = FetchType.LAZY)
    @JoinColumn(name = "GROUP_ID", insertable = true, updatable = true, nullable = true)
    private GroupEntity group;

    @ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH }, fetch = FetchType.LAZY)
    @JoinColumn(name = "ORGANIZATION_ID", insertable = true, updatable = true, nullable = true)
    private OrganizationEntity organization;

    @ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH }, fetch = FetchType.LAZY)
    @JoinColumn(name = "SETTING_MAIL_ID", insertable = true, updatable = true, nullable = true)
    private SettingMailEntity settingMail;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getText() {
        return text;
    }

    public Date getDateOn() {
        return dateOn;
    }

    public NotificationType getNotificationType() {
        return notificationType;
    }

    public Status getStatus() {
        return status;
    }

    public UserEntity getUser() {
        return user;
    }

    public GroupEntity getGroup() {
        return group;
    }

    public OrganizationEntity getOrganization() {
        return organization;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setDateOn(Date dateOn) {
        this.dateOn = dateOn;
    }

    public void setNotificationType(NotificationType notificationType) {
        this.notificationType = notificationType;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public void setGroup(GroupEntity group) {
        this.group = group;
    }

    public void setOrganization(OrganizationEntity organization) {
        this.organization = organization;
    }

    public SettingMailEntity getSettingMail() {
        return settingMail;
    }

    public void setSettingMail(SettingMailEntity settingMail) {
        this.settingMail = settingMail;
    }

    public NotificationUserType getUserType() {
        return userType;
    }

    public void setUserType(NotificationUserType userType) {
        this.userType = userType;
    }
}
