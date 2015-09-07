package com.my3o.frontend.web.model;

import java.util.Date;

import com.my3o.common.constant.NotificationType;
import com.my3o.common.constant.NotificationUserType;

public class NotificationWebModel extends ReferenceBookModel {

    private Date data;
    private NotificationType type;
    private String text;
    private String settingMailId;
    private String userId;
    private String groupId;
    private String organizationId;
    private NotificationUserType userType;
    private String date;

    public NotificationType getType() {
        return type;
    }

    public String getText() {
        return text;
    }

    public String getUserId() {
        return userId;
    }

    public String getGroupId() {
        return groupId;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setType(NotificationType type) {
        this.type = type;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getSettingMailId() {
        return settingMailId;
    }

    public void setSettingMailId(String settingMailId) {
        this.settingMailId = settingMailId;
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
}
