package com.my3o.common.dto;

import java.util.Date;

import com.my3o.common.constant.NotificationType;
import com.my3o.common.constant.NotificationUserType;
import com.my3o.common.constant.Status;

/**
 * Created by: andrew
 */
public class NotificationDto {
    private String id;
    private String name;
    private String description;
    private String text;
    private Date dateOn;
    private NotificationType notificationType;
    private Status status;
    private NotificationUserType userType;
    private SettingMailDto settingMailDto;
    private UserDto userDto;
    private GroupDto groupDto;
    private OrganizationDto organizationDto;
    private String date;

    private Date createTime;
    private Date lastUpdateTime;

    private String createByUserId;
    private String updateByUserId;

    private String cronExpression = "0/5 * * * * ?";

    public String getId() {
        return id;
    }

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

    public UserDto getUserDto() {
        return userDto;
    }

    public GroupDto getGroupDto() {
        return groupDto;
    }

    public OrganizationDto getOrganizationDto() {
        return organizationDto;
    }

    public void setId(String id) {
        this.id = id;
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

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }

    public void setGroupDto(GroupDto groupDto) {
        this.groupDto = groupDto;
    }

    public void setOrganizationDto(OrganizationDto organizationDto) {
        this.organizationDto = organizationDto;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public String getCreateByUserId() {
        return createByUserId;
    }

    public String getUpdateByUserId() {
        return updateByUserId;
    }

    public void setCreateByUserId(String createByUserId) {
        this.createByUserId = createByUserId;
    }

    public void setUpdateByUserId(String updateByUserId) {
        this.updateByUserId = updateByUserId;
    }

    public SettingMailDto getSettingMailDto() {
        return settingMailDto;
    }

    public void setSettingMailDto(SettingMailDto settingMailDto) {
        this.settingMailDto = settingMailDto;
    }

    public NotificationUserType getUserType() {
        return userType;
    }

    public void setUserType(NotificationUserType userType) {
        this.userType = userType;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "NotificationDto{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", text='" + text + '\'' +
                ", dateOn=" + dateOn +
                ", notificationType=" + notificationType +
                ", status=" + status +
                ", userType=" + userType +
                ", settingMailDto=" + settingMailDto +
                ", userDto=" + userDto +
                ", groupDto=" + groupDto +
                ", organizationDto=" + organizationDto +
                ", createTime=" + createTime +
                ", lastUpdateTime=" + lastUpdateTime +
                ", createByUserId='" + createByUserId + '\'' +
                ", updateByUserId='" + updateByUserId + '\'' +
                ", cronExpression='" + cronExpression + '\'' +
                '}';
    }

    public NotificationDto() {
    }

    public NotificationDto(String id) {
        this.id = id;
    }

    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NotificationDto that = (NotificationDto) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
